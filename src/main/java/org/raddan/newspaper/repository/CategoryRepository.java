package org.raddan.newspaper.repository;

import org.raddan.newspaper.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllById(Long categoryId);
}
