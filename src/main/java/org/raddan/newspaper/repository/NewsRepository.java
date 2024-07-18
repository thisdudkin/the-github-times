package org.raddan.newspaper.repository;

import org.raddan.newspaper.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Integer> {
    @Query("SELECT n FROM News n WHERE n.id = :p_newsId")
    Optional<News> findById(@Param("p_newsId") Long newsId);

    @Query("SELECT n FROM News n WHERE n.author.id = :p_authorId")
    List<News> findAllByAuthor(@Param("p_authorId") Long authorId);

    @Query("SELECT n FROM News n WHERE JSONB_QUERY(n.data, '$.title') = :p_title")
    Optional<News> findByTitle(@Param("p_title") String title);

    // TODO: get all news for day (e.g: 2013-11-30)
}
