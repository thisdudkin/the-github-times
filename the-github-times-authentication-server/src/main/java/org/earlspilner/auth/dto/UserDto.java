package org.earlspilner.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.earlspilner.auth.security.UserRole;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * @author Alexander Dudkin
 */
@JsonInclude(NON_NULL)
public record UserDto(
        @NotNull
        @JsonProperty(access = READ_ONLY)
        Integer id,
        @NotNull
        String username,
        @Email
        @JsonProperty(access = READ_ONLY)
        @NotNull
        String email,
        @NotNull
        String password,
        @JsonProperty(access = READ_ONLY)
        String createdUtc,
        @JsonProperty(access = READ_ONLY)
        String updatedUtc,
        @NotNull
        List<UserRole> userRoles
) { }
