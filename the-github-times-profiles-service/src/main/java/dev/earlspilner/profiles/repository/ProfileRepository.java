package dev.earlspilner.profiles.repository;

import dev.earlspilner.profiles.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Alexander Dudkin
 */
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
}
