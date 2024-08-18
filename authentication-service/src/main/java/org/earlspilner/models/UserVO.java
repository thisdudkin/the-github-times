package org.earlspilner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alexander Dudkin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String id;
    private String email;
    private String password;
    private String role;
}
