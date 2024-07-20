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

    @PostMapping(path = "/create")
    public NewsCreationResponse createNews(@RequestBody NewsData request) {
        return newsService.createNews(request);
    }

    @GetMapping
    public List<NewsInfoResponse> getAllNews() {
        return newsService.getAllNewsInfo();
    }

    @GetMapping(path = "/id/{newsId}")
    public NewsInfoResponse getNewsInfo(@PathVariable String newsId) {
        return newsService.getNewsInfo(newsId);
    }

    @GetMapping(path = "/tags/{tag}")
    public List<NewsInfoResponse> getNewsInfoByTag(@PathVariable String tag) {
        return newsService.getNewsInfoByTag(tag);
    }

    @GetMapping(path = "/author/{author}")
    public List<NewsInfoResponse> getNewsInfoByAuthor(@PathVariable String author) {
        return newsService.getNewsInfoByAuthor(author);
    }

    @DeleteMapping(path = "/delete/{newsId}")
    public DeletionResponse deleteNews(@PathVariable String newsId) {
        return newsService.deleteNews(newsId);
    }

}
