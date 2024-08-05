package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.ArticleDTO;
import org.raddan.newspaper.entity.Article;
import org.raddan.newspaper.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article get(@PathVariable Long id) {
        return articleService.getById(id);
    }

    @PostMapping("/create")
    public Article create(@RequestBody ArticleDTO dto) {
        return articleService.create(dto);
    }

    @PutMapping("/{id}/edit")
    public Article update(@PathVariable Long id, @RequestBody ArticleDTO dto) {
        return articleService.update(id, dto);
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        return articleService.delete(id);
    }
}
