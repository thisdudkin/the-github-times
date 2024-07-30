package org.raddan.newspaper.repository;

import org.raddan.newspaper.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.title = :p_title")
    Optional<Article> findByTitle(@Param("p_title") String title);
}
