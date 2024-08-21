package org.earlspilner.users.service;

import jakarta.servlet.http.HttpServletRequest;
import org.earlspilner.users.dto.AuthRequest;
import org.earlspilner.users.dto.RegisterRequest;
import org.earlspilner.users.dto.Tokens;
import org.earlspilner.users.model.User;

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
}
