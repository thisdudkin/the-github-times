package org.raddan.newspaper.repository;

import org.raddan.newspaper.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE p.user.id = :p_userId")
    Optional<Profile> findByUser(@Param("p_userId") Long userId);
}
