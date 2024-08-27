package org.earlspilner.authentication.service;

import org.earlspilner.authentication.rest.dto.AuthenticationRequest;
import org.earlspilner.authentication.rest.dto.Tokens;

/**
 * @author Alexander Dudkin
 */
public interface AuthenticationService {
    Tokens login(AuthenticationRequest request);
    Tokens refresh(String refreshToken);
}
