package org.earlspilner.articles.repository;

import org.earlspilner.articles.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
