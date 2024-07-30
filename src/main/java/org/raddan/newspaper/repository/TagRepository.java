package org.raddan.newspaper.repository;

import org.raddan.newspaper.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllById(Long id);

    @Query("SELECT t FROM Tag t WHERE t.name = :p_name")
    Optional<Tag> findByName(@Param("p_name") String name);
}
