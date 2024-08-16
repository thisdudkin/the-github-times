package org.raddan.auth.repository;

import jakarta.transaction.Transactional;
import org.raddan.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    boolean existsByUsername(String username);

    AppUser findByUsername(String username);

    @Transactional
    void deleteByUsername(String username);
}
