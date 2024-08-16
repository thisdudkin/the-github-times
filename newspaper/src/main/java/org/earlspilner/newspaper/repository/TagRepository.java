package org.earlspilner.newspaper.repository;

import org.earlspilner.newspaper.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
public interface TagRepository extends JpaRepository<Tag, Integer> {
    Optional<Tag> findByName(String name);
    boolean existsByName(String name);
}
