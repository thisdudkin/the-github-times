package org.earlspilner.authentication.rest.dto;

/**
 * @author Alexander Dudkin
 */
public record Tokens(String accessToken, String refreshToken) {
}
