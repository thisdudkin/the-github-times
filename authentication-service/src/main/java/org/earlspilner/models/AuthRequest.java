package org.earlspilner.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alexander Dudkin
 */
@Data
@AllArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
    private String name;
}
