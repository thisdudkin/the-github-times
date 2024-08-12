package org.raddan.newspaper.repository;

import org.raddan.newspaper.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface ArticleRepository extends JpaRepository<Article, Long> { }
