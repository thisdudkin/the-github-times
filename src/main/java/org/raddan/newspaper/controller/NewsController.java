package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.service.NewsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/news/")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

}
