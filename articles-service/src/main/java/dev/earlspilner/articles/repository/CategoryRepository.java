package dev.earlspilner.articles.repository;

import dev.earlspilner.articles.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
