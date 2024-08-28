package dev.earlspilner.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

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
        @NotNull
        String email,
        @NotNull
        String password,
        @JsonProperty(access = READ_ONLY)
        String createdUtc,
        @JsonProperty(access = READ_ONLY)
        String updatedUtc
) { }
