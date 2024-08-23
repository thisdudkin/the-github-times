package org.earlspilner.users.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Alexander Dudkin
 */
public enum UserRole implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
