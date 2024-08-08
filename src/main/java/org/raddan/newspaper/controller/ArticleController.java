package org.raddan.newspaper.controller;

import lombok.RequiredArgsConstructor;
import org.raddan.newspaper.dto.ArticleDTO;
import org.raddan.newspaper.model.Article;
import org.raddan.newspaper.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * Retrieves a list of all articles.
     *
     * @return a list of articles.
     */
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    /**
     * Retrieves an article by its ID.
     *
     * @param id the ID of the article.
     * @return the article with the specified ID.
     */
    @GetMapping("/{id}")
    public Article get(@PathVariable Long id) {
        return articleService.getById(id);
    }

    /**
     * Creates a new article.
     *
     * @param dto the data transfer object containing article details.
     * @return the created article.
     */
    @PostMapping("/create")
    public Article create(@RequestBody ArticleDTO dto) {
        return articleService.create(dto);
    }

    /**
     * Updates an existing article.
     *
     * @param id  the ID of the article to be updated.
     * @param dto the data transfer object containing updated article details.
     * @return the updated article.
     */
    @PutMapping("/{id}/edit")
    public Article update(@PathVariable Long id, @RequestBody ArticleDTO dto) {
        return articleService.update(id, dto);
    }

    /**
     * Deletes an article by its ID.
     *
     * @param id the ID of the article to be deleted.
     * @return a message indicating the result of the deletion.
     */
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        return articleService.delete(id);
    }
}
