package dev.earlspilner.articles.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.earlspilner.articles.entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * @author Alexander Dudkin
 */
@JsonInclude(NON_NULL)
public record UserDto(
        @NotNull
        Integer id,
        @NotNull
        String username,
        @Email
        @NotNull
        String email,
        @NotNull
        String password,
        @JsonProperty(access = READ_ONLY)
        String createdUtc,
        @JsonProperty(access = READ_ONLY)
        String updatedUtc,
        @JsonProperty(access = READ_ONLY)
        List<UserRole> userRoles
) { }
