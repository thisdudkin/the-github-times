package org.earlspilner.authentication.security;

import org.earlspilner.authentication.rest.dto.UserResponse;
import org.earlspilner.authentication.service.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Dudkin
 */
@Component
public class CustomUserDetails implements UserDetailsService {

    private final UserServiceClient userServiceClient;

    @Autowired
    public CustomUserDetails(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserResponse user = userServiceClient.getUserByUsername(username);
        if (user == null) {
            // TODO
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return User
                .withUsername(username)
                .password(user.password())
                .authorities(user.userRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
