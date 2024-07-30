package org.raddan.newspaper.repository;

import org.raddan.newspaper.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllById(Long id);
}
