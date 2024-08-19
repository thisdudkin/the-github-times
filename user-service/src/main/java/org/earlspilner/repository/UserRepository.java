package org.earlspilner.repository;

import org.earlspilner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}
