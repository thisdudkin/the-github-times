package org.raddan.newspaper.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.News;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.response.creation.NewsCreationResponse;
import org.raddan.newspaper.entity.response.deletion.DeletionResponse;
import org.raddan.newspaper.entity.response.info.NewsInfoResponse;
import org.raddan.newspaper.filter.DateFilter;
import org.raddan.newspaper.repository.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsService.class);
    
    private final NewsRepository newsRepository;
    private final UserService userService;

    public NewsCreationResponse createNews(NewsData request) {
        var news = new News();
        news.setId(String.valueOf(UUID.randomUUID()));
        news.setAuthor(userService.getCurrentUser());
        news.setCreatedUtc(Instant.now().getEpochSecond());
        news.setUpdatedUtc(Instant.now().getEpochSecond());

        news.setData(request);
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

    public List<NewsInfoResponse> getNewsInfoByTag(String tag) {
        List<News> newsList = newsRepository.findByTag(tag);
        if (newsList.isEmpty())
            throw new EntityNotFoundException("News with tag: " + tag + " not found");

        return newsList.stream().map(news -> new NewsInfoResponse(
                news.getId(),
                news.getData().getTitle(),
                news.getData().getSummary(),
                news.getData().getContent(),
                news.getData().getTags(),
                news.getData().getImageURL(),
                news.getAuthor().getUsername(),
                DateFilter.formatInstant(news.getCreatedUtc())
        )).toList();
    }

    public List<NewsInfoResponse> getNewsInfoByAuthor(String author) {
        List<News> newsList = newsRepository.findAllByAuthor(author);
        if (newsList.isEmpty())
            throw new EntityNotFoundException("News with author: " + author + " not found");

        return newsList.stream().map(news -> new NewsInfoResponse(
                news.getId(),
                news.getData().getTitle(),
                news.getData().getSummary(),
                news.getData().getContent(),
                news.getData().getTags(),
                news.getData().getImageURL(),
                news.getAuthor().getUsername(),
                DateFilter.formatInstant(news.getCreatedUtc())
        )).toList();
    }

    public List<NewsInfoResponse> getAllNewsInfo() {
        List<News> newsList = newsRepository.findAll();
        if (newsList.isEmpty())
            throw new EntityNotFoundException("News not found");

        return newsList.stream().map(news -> new NewsInfoResponse(
                news.getId(),
                news.getData().getTitle(),
                news.getData().getSummary(),
                news.getData().getContent(),
                news.getData().getTags(),
                news.getData().getImageURL(),
                news.getAuthor().getUsername(),
                DateFilter.formatInstant(news.getCreatedUtc())
        )).toList();
    }

    public DeletionResponse deleteNews(String newsId) {
        Optional<News> optionalNews = newsRepository.findById(newsId);
        if (optionalNews.isEmpty())
            throw new EntityNotFoundException("News with UUID " + newsId + " not found");

        var news = optionalNews.get();
        newsRepository.delete(optionalNews.get());
        log.info("News with UUID {} deleted successfully", newsId);
        log.info("Author: {}", optionalNews.get().getAuthor().getUsername());

        return new DeletionResponse(
                news.getId(),
                "Type: News",
                DateFilter.formatInstant(Instant.now().getEpochSecond()),
                news.getAuthor().getUsername()
        );
    }
}
