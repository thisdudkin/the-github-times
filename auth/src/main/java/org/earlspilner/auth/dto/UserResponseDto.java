package org.earlspilner.auth.dto;

import lombok.Data;
import org.earlspilner.auth.model.AppUserRole;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Data
public class UserResponseDto {

    private Integer id;
    private String username;
    private String email;
    List<AppUserRole> appUserRoles;

}
