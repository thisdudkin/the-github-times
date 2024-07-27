package org.raddan.newspaper.repository;

import jakarta.transaction.Transactional;
import org.raddan.newspaper.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Alexander Dudkin
 */
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    /**
     * Abstract method to get Profile by username.
     *
     * @param username of Profile's author.
     * @return {@code Profile} from the database
     */
    @Query("SELECT p FROM Profile p WHERE p.user.username = :p_username")
    Optional<Profile> findByUsername(@Param("p_username") String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Profile p WHERE p.user.id = :p_userId")
    void deleteEntity(@Param("p_userId") Long userId);
}
