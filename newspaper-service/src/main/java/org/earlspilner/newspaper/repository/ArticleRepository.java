package org.earlspilner.newspaper.repository;

import org.earlspilner.newspaper.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    @Query("SELECT a from Article a WHERE a.createdUtc BETWEEN :startDate AND :endDate")
    Collection<Article> findByDateBetween(Instant startDate, Instant endDate);

    Optional<Article> findByTitle(String title);

    @Query("SELECT a FROM Article a JOIN a.tags t WHERE t.name = :tagName")
    Collection<Article> findArticlesByTagName(@Param("tagName") String tagName);

    @Query("SELECT a FROM Article a JOIN a.categories c WHERE c.name = :categoryName")
    Collection<Article> findArticlesByCategoryName(@Param("categoryName") String categoryName);

    @Query("SELECT a FROM Article a WHERE a.title LIKE %:keyword%")
    Collection<Article> findArticlesWithKeyword(String keyword);

//    @Query("SELECT a FROM Article a WHERE a.appUser.username = :author")
//    Collection<Article> findArticlesByAuthor(@Param("author") String author);

//    @Query("SELECT a FROM Article a JOIN a.comments c GROUP BY a HAVING COUNT(c) >= :limit")
//    Collection<Article> findArticlesByCommentCount(int limit);

    default Collection<Article> findRecentArticles() {
        Instant now = Instant.now();
        Instant threeDaysAgo = now.minus(3, ChronoUnit.DAYS);
        return findByDateBetween(threeDaysAgo, now);
    }
}
