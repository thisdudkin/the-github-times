package org.raddan.newspaper.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.News;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.response.creation.NewsCreationResponse;
import org.raddan.newspaper.entity.response.info.NewsInfoResponse;
import org.raddan.newspaper.filter.DateFilter;
import org.raddan.newspaper.repository.NewsRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.util.Optional;
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

    public NewsInfoResponse getNewsInfo(String uuid) {
        Optional<News> optionalNews = newsRepository.findById(uuid);
        if (optionalNews.isEmpty())
            throw new EntityNotFoundException("News with UUID " + uuid + " not found");

        var news = optionalNews.get();
        return new NewsInfoResponse(
                news.getId(),
                news.getData().getTitle(),
                news.getData().getSummary(),
                news.getData().getContent(),
                news.getData().getTags(),
                news.getData().getImageURL(),
                news.getAuthor().getUsername(),
                DateFilter.formatInstant(news.getCreatedUtc())
        );
    }
}
