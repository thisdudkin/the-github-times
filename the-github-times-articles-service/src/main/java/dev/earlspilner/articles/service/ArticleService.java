package dev.earlspilner.articles.service;

import dev.earlspilner.articles.dto.ArticleDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Alexander Dudkin
 */
public interface ArticleService {
    ArticleDto addArticle(HttpServletRequest request, ArticleDto articleDto);
    ArticleDto getArticle(Integer id);
    Page<ArticleDto> getArticles(Pageable pageable);
    ArticleDto updateArticle(Integer id, ArticleDto articleDto);
    void deleteArticle(Integer id);
}
