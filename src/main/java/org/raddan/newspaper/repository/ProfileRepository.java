package org.raddan.newspaper.repository;

import org.raddan.newspaper.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
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

}
