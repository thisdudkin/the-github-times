package org.raddan.newspaper.repository;

import org.raddan.newspaper.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

}
