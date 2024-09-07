package dev.earlspilner.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.earlspilner.users.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * Data Transfer Object for User.
 * Provides information about a user, with fields that may be required or optional.
 *
 * @param id         Unique identifier for the user, not null.
 * @param username   Username of the user, not null.
 * @param email      Email of the user, must be a valid email and not null.
 * @param password   Password of the user, not null.
 * @param createdUtc Timestamp of when the user was created, may be null.
 * @param updatedUtc Timestamp of the last update to the user, may be null.
 * @param userRoles  List of roles associated with the user, may be null.
 *
 * @author Alexander Dudkin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        @NotNull Integer id,
        @NotNull String username,
        @Email @NotNull String email,
        @NotNull String password,
        String createdUtc,
        String updatedUtc,
        List<UserRole> userRoles
) { }
