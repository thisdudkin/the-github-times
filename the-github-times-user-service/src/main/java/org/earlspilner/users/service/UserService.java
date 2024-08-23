package org.earlspilner.users.service;

import jakarta.servlet.http.HttpServletRequest;
import org.earlspilner.users.rest.dto.request.AuthRequest;
import org.earlspilner.users.rest.dto.request.RegisterRequest;
import org.earlspilner.users.rest.dto.response.Tokens;
import org.earlspilner.users.model.User;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @author Alexander Dudkin
 */
public interface UserService {
    Tokens login(AuthRequest request);
    Tokens register(RegisterRequest request);
    void delete(String username);
    User search(String username);
    User whoami(HttpServletRequest request);
    Tokens refresh(String username);

    List<User> getUsers();
    User getAuthenticatedUser(Authentication authentication);
}
