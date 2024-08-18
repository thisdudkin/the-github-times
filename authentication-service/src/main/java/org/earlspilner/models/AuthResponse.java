package org.earlspilner.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Alexander Dudkin
 */
@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
