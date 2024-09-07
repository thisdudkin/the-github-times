package org.earlspilner.auth.dto;

import jakarta.validation.constraints.NotNull;

/**
 * @author Alexander Dudkin
 */
public record Tokens(
        @NotNull
        String accessToken,
        @NotNull
        String refreshToken
) { }
