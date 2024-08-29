package org.earlspilner.auth.security;

import lombok.RequiredArgsConstructor;
import org.earlspilner.auth.dto.UserDto;
import org.earlspilner.auth.service.UserServiceClient;
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

    private final UserServiceClient feignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userResponse = feignClient.getUserByUsername(username);
        if (userResponse == null)
            throw new UsernameNotFoundException("User not found with username: " + username);

        return User.withUsername(username)
                .password(userResponse.password())
                .authorities(userResponse.userRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
