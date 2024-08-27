package org.earlspilner.authentication.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.earlspilner.authentication.security.UserRole;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
public record UserResponse(Integer id,
                           String username,
                           String email,
                           String password,
                           List<UserRole> userRoles
) { }
