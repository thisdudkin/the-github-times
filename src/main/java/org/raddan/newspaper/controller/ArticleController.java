package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.ArticleDTO;
import org.raddan.newspaper.entity.Article;
import org.raddan.newspaper.service.ArticleService;
import org.springframework.context.ApplicationContext;
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
    public Article createArticle(@RequestBody ArticleDTO dto) {
        return articleService.create(dto);
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.getById(id);
    }

    @PutMapping("/{id}")
    public Article updateById(@PathVariable Long id, @RequestBody ArticleDTO dto) {
        return articleService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        return articleService.deleteById(id);
    }
}
