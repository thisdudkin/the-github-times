package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.response.creation.NewsCreationResponse;
import org.raddan.newspaper.entity.response.deletion.DeletionResponse;
import org.raddan.newspaper.entity.response.info.NewsInfoResponse;
import org.raddan.newspaper.service.NewsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping(path = "/create")
    public NewsCreationResponse createNews(@RequestBody NewsData request) {
        return newsService.createNews(request);
    }

    @GetMapping(path = "/{newsId}")
    public NewsInfoResponse getNewsInfo(@PathVariable String newsId) {
        return newsService.getNewsInfo(newsId);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(path = "/{newsId}")
    public DeletionResponse deleteResponse(@PathVariable String newsId) {
        return newsService.deleteNews(newsId);
    }

}
