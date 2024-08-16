package org.earlspilner.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import org.earlspilner.auth.model.AppUser;
import org.springframework.http.ResponseEntity;

/**
 * @author Alexander Dudkin
 */
public interface UserService {
    ResponseEntity<?> login(String username, String password);
    ResponseEntity<?> register(AppUser appUser);
    ResponseEntity<Void> delete(String username);
    ResponseEntity<?> search(String username);
    ResponseEntity<?> whoami(HttpServletRequest req);
    ResponseEntity<?> refresh(String username);
    AppUser getAuthorizedUser();
}
