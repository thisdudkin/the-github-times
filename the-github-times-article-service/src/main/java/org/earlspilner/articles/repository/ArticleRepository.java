package org.earlspilner.articles.repository;

import org.earlspilner.articles.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
