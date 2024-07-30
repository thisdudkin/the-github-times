package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.ArticleDTO;
import org.raddan.newspaper.entity.Article;
import org.raddan.newspaper.service.ArticleService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/create")
    public Article create(@RequestBody ArticleDTO dto) {
        return articleService.create(dto);
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable Long id) {
        return articleService.getById(id);
    }
}
