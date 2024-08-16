package org.raddan.auth.dto;

import lombok.Data;
import org.raddan.auth.model.AppUserRole;

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
