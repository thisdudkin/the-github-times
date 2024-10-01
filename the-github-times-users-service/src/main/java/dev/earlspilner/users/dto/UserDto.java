package dev.earlspilner.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.earlspilner.users.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * @author Alexander Dudkin
 */
@JsonInclude(NON_NULL)
public record UserDto(
        Integer id,
        @NotBlank String username,
        @Email @NotBlank String email,
        @NotBlank String password,
        String createdUtc,
        String updatedUtc,
        List<UserRole> userRoles
) { }
