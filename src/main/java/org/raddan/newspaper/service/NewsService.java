package org.raddan.newspaper.service;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.News;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.response.NewsCreationResponse;
import org.raddan.newspaper.filter.DateFilter;
import org.raddan.newspaper.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserService userService;

    public NewsCreationResponse createNews(NewsData request) {
        News news = new News();
        news.setId(String.valueOf(UUID.randomUUID()));
        news.setAuthor(userService.getCurrentUser());
        news.setCreatedUtc(Instant.now().getEpochSecond());
        news.setUpdatedUtc(Instant.now().getEpochSecond());
        news.setData(request);

        System.out.println(news.getData());

        newsRepository.save(news);

        return new NewsCreationResponse(
                String.valueOf(news.getId()),
                news.getData().getTitle(),
                news.getData().getSummary(),
                news.getData().getContent()
        );
    }
}
