package org.raddan.newspaper.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.Category;
import org.raddan.newspaper.entity.News;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.response.creation.NewsCreationResponse;
import org.raddan.newspaper.entity.response.deletion.DeletionResponse;
import org.raddan.newspaper.entity.response.info.NewsInfoResponse;
import org.raddan.newspaper.exception.custom.ArticleNotFoundException;
import org.raddan.newspaper.filter.DateFilter;
import org.raddan.newspaper.repository.CategoryRepository;
import org.raddan.newspaper.repository.NewsRepository;
import org.raddan.newspaper.utils.ArticleFieldUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsService.class);

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;
    private final UserService userService;

    @Autowired
    private ArticleFieldUpdater fieldUpdater;

    @Autowired
    private DateFilter dateFilter;

    public NewsInfoResponse createNews(NewsData request) {
        var news = new News();
        news.setId(String.valueOf(UUID.randomUUID()));
        news.setAuthor(userService.getCurrentUser());
        news.setCreatedUtc(Instant.now().getEpochSecond());
        news.setUpdatedUtc(Instant.now().getEpochSecond());
        news.setData(request);

        var category = new Category();
        category.setName(request.getCategory());

        newsRepository.save(news);
        categoryRepository.save(category);
        return creationResponse(news);
    }

    public NewsInfoResponse getNewsInfo(String uuid) {
        Optional<News> optionalNews = newsRepository.findById(uuid);
        if (optionalNews.isEmpty())
            throw new EntityNotFoundException("News with UUID " + uuid + " not found");

        var news = optionalNews.get();
        return creationResponse(news);
    }

    public List<NewsInfoResponse> getNewsInfoByTag(String tag) {
        List<News> newsList = newsRepository.findByTag(tag);
        if (newsList.isEmpty())
            throw new EntityNotFoundException("News with tag: " + tag + " not found");

        return newsList.stream().map(this::creationResponse).toList();
    }

    public List<NewsInfoResponse> getNewsInfoByAuthor(String author) {
        List<News> newsList = newsRepository.findAllByAuthor(author);
        if (newsList.isEmpty())
            throw new EntityNotFoundException("News with author: " + author + " not found");

        return newsList.stream().map(this::creationResponse).toList();
    }

    public List<NewsInfoResponse> getAllNewsInfo() {
        List<News> newsList = newsRepository.findAll();
        if (newsList.isEmpty())
            throw new EntityNotFoundException("News not found");

        return newsList.stream().map(this::creationResponse).toList();
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
                dateFilter.formatInstant(Instant.now().getEpochSecond()),
                news.getAuthor().getUsername()
        );
    }

    public NewsInfoResponse editNews(String UUID, Map<String, Object> requestInfo) {
        News news = newsRepository.findById(UUID)
                .orElseThrow(() -> new ArticleNotFoundException("Article not found!"));
        fieldUpdater.update(news, requestInfo);
        news.setUpdatedUtc(Instant.now().getEpochSecond());
        newsRepository.save(news);
        return creationResponse(news);
    }

    private NewsInfoResponse creationResponse(News news) {
        long createdUtc = news.getCreatedUtc();
        long updatedUtc = news.getUpdatedUtc();
        return new NewsInfoResponse(
                news.getId(),
                news.getAuthor().getUsername(),
                dateFilter.formatInstant(createdUtc),
                dateFilter.formatInstant(updatedUtc),
                news.getData()
        );
    }
}
