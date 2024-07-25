package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.response.deletion.DeletionResponse;
import org.raddan.newspaper.entity.response.info.NewsInfoResponse;
import org.raddan.newspaper.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <b>RestController</b> for managing requests & responses
 * for CRUD operations.
 *
 * @author Alexander Dudkin
 * @see NewsService
 */
@RestController
@RequestMapping(path = "/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    /**
     * Get all news articles
     *
     * @return {@code List<NewsInfoResponse>} - list of all recent articles
     */
    @GetMapping
    public List<NewsInfoResponse> getAllNews() {
        return newsService.getAllNewsInfo();
    }

    /**
     * Return the article for news with: {@code q} - param, which
     * stands for UUID of article
     *
     * @param q param, which stands for <b>UUID</b> of article
     * @return {@code Article}
     */
    @GetMapping(path = "/search")
    public NewsInfoResponse getNewsInfo(@RequestParam String q) {
        return newsService.getNewsInfo(q);
    }

    /**
     * Return the {@code List<Article>} for news with: {@code q} - param, which
     * stands for <b>tag</b> of article
     *
     * @param q param, which stands for <b>tag</b> of article
     * @return {@code List<Article>}
     */
    @GetMapping(path = "/tags")
    public List<NewsInfoResponse> getNewsInfoByTag(@RequestParam String q) {
        return newsService.getNewsInfoByTag(q);
    }

    /**
     * Return the {@code List<Article>} for news with: {@code q} - param, which
     * stands for <b>author</b> of article
     *
     * @param q param, which stands for <b>author</b> of article
     * @return {@code List<Article>}
     */
    @GetMapping(path = "/author")
    public List<NewsInfoResponse> getNewsInfoByAuthor(@RequestParam String q) {
        return newsService.getNewsInfoByAuthor(q);
    }

    /**
     * Create a new article
     *
     * @param request {@literal not null}
     * @return {@code Article} - all needed news info for reader
     */
    @PostMapping(path = "/create")
    public NewsInfoResponse createNews(@RequestBody NewsData request) {
        return newsService.createNews(request);
    }

    @PatchMapping(path = "/{UUID}/edit")
    public NewsInfoResponse editNews(@PathVariable String UUID, @RequestBody Map<String, Object> requestInfo) {
        return newsService.editNews(UUID, requestInfo);
    }

    /**
     * Delete news article with <b>UUID</b>, which is located
     * in {@code @PathVariable}
     *
     * @param newsId {@literal not null}
     * @return {@code DeletionResponse}
     */
    @DeleteMapping(path = "/delete/{newsId}")
    public DeletionResponse deleteNews(@PathVariable String newsId) {
        return newsService.deleteNews(newsId);
    }

}
