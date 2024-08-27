package org.earlspilner.users.service;

import jakarta.servlet.http.HttpServletRequest;
import org.earlspilner.users.rest.dto.request.AuthRequest;
import org.earlspilner.users.rest.dto.request.RegisterRequest;
import org.earlspilner.users.model.User;
import org.earlspilner.users.rest.dto.response.UserResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
public interface UserService {
    UserResponse register(RegisterRequest request);
    void delete(String username);
    UserResponse search(String username);
    UserResponse whoami(HttpServletRequest request);

    List<User> getUsers();
    User getAuthenticatedUser(Authentication authentication);
}
