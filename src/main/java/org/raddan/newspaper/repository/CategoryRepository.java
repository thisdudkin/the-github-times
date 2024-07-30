package org.raddan.newspaper.repository;

import org.raddan.newspaper.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllById(Long categoryId);

    @Query("SELECT c FROM Category c WHERE c.name = :p_name")
    Optional<Category> findByName(@Param("p_name") String name);
}
