package org.earlspilner.auth.security;

import lombok.RequiredArgsConstructor;
import org.earlspilner.auth.dto.UserDto;
import org.earlspilner.auth.feign.UserServiceClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Alexander Dudkin
 */
@Component
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {

    private final UserServiceClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserDto user = userClient.getUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found with username: " + username);

        return User.withUsername(username)
                .password(user.password())
                .authorities(user.userRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
