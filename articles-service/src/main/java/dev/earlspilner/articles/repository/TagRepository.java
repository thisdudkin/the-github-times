package dev.earlspilner.articles.repository;

import dev.earlspilner.articles.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
