package org.earlspilner.users.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alexander Dudkin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tokens {
    private String accessToken;
    private String refreshToken;
}