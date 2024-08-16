package org.earlspilner.newspaper.repository;

import org.earlspilner.newspaper.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Collection;

/**
 * @author Alexander Dudkin
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("SELECT a from Article a WHERE a.createdUtc BETWEEN :startDate AND :endDate")
    Collection<Article> findByDateBetween(Instant startDate, Instant endDate);
}
