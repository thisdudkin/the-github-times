package org.earlspilner.articles.repository;

import org.earlspilner.articles.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
