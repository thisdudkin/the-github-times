package org.earlspilner.auth.service;

import org.earlspilner.auth.dto.AuthDto;
import org.earlspilner.auth.dto.Tokens;

/**
 * @author Alexander Dudkin
 */
public interface AuthenticationServer {
    Tokens login(AuthDto authDto);
    Tokens refresh(String refreshToken);
}
