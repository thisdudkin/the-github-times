package org.earlspilner.auth.repository;

import jakarta.transaction.Transactional;
import org.earlspilner.auth.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface UserRepository extends JpaRepository<AppUser, Integer> {
    @Transactional
    void deleteByUsername(String username);
    boolean existsByUsername(String username);
    AppUser findByUsername(String username);
}
