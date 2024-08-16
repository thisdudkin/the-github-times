package org.earlspilner.auth.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Alexander Dudkin
 */
public enum AppUserRole implements GrantedAuthority {
    ROLE_ADMIN, ROLE_CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }

}
