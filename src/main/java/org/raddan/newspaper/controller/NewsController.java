package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.response.creation.NewsCreationResponse;
import org.raddan.newspaper.entity.response.deletion.DeletionResponse;
import org.raddan.newspaper.entity.response.info.NewsInfoResponse;
import org.raddan.newspaper.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<NewsInfoResponse> getAllNews() {
        return newsService.getAllNewsInfo();
    }

    @PostMapping(path = "/create")
    public NewsCreationResponse createNews(@RequestBody NewsData request) {
        return newsService.createNews(request);
    }

    @GetMapping(path = "/search")
    public NewsInfoResponse getNewsInfo(@RequestParam String q) {
        return newsService.getNewsInfo(q);
    }

    @GetMapping(path = "/tags")
    public List<NewsInfoResponse> getNewsInfoByTag(@RequestParam String q) {
        return newsService.getNewsInfoByTag(q);
    }

    @GetMapping(path = "/author")
    public List<NewsInfoResponse> getNewsInfoByAuthor(@RequestParam String q) {
        return newsService.getNewsInfoByAuthor(q);
    }

    @DeleteMapping(path = "/delete/{newsId}")
    public DeletionResponse deleteNews(@PathVariable String newsId) {
        return newsService.deleteNews(newsId);
    }

}
