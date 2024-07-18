package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.entity.data.NewsData;
import org.raddan.newspaper.entity.response.NewsCreationResponse;
import org.raddan.newspaper.service.NewsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/news/")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping(path = "/create")
    public NewsCreationResponse createNews(@RequestBody NewsData request) {
        return newsService.createNews(request);
    }

}
