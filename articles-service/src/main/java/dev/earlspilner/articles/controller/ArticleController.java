package dev.earlspilner.articles.controller;

import dev.earlspilner.articles.dto.ArticleDto;
import dev.earlspilner.articles.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alexander Dudkin
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleDto> addArticle(@RequestBody ArticleDto articleDto, HttpServletRequest request) {
        return new ResponseEntity<>(articleService.addArticle(request, articleDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(articleService.getArticle(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ArticleDto>> getArticles(Pageable pageable) {
        return new ResponseEntity<>(articleService.getArticles(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDto> updateArticle(@PathVariable("id") Integer id, @RequestBody ArticleDto articleDto) {
        return new ResponseEntity<>(articleService.updateArticle(id, articleDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
