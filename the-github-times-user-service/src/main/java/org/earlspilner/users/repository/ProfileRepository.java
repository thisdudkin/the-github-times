package org.earlspilner.users.repository;

import org.earlspilner.users.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
