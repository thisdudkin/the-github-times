package org.earlspilner.newspaper.repository;

import org.earlspilner.newspaper.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    @Query("SELECT p FROM Profile p WHERE p.firstName = :p_firstName")
    List<Profile> findAllProfilesByFirstName(@Param("p_firstName") String firstName);

    @Query("SELECT p FROM Profile p WHERE p.lastName = :p_lastName")
    List<Profile> findAllProfilesByLastName(@Param("p_lastName") String lastName);
}
