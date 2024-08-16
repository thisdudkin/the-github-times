package org.earlspilner.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.earlspilner.auth.model.AppUserRole;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
@Data
@NoArgsConstructor
public class UserDataDto {

    private String username;
    private String email;
    private String password;
    List<AppUserRole> appUserRoles;

}
