package org.earlspilner.users.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.earlspilner.users.model.UserRole;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private List<UserRole> userRoles;
}
