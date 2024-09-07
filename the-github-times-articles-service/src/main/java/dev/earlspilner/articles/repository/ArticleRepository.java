package dev.earlspilner.articles.repository;

import dev.earlspilner.articles.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
