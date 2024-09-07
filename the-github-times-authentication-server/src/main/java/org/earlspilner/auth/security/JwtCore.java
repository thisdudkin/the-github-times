package org.earlspilner.auth.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.earlspilner.auth.dto.Tokens;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Alexander Dudkin
 */
@Service
@RequiredArgsConstructor
public class JwtCore {

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.expiration.access}")
    private Long accessExpiration;

    @Value("${jwt.expiration.refresh}")
    private Long refreshExpiration;

    private Key key;

    private JwtParser jwtParser;

    private final CustomUserDetails customUserDetails;

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey.getBytes());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.jwtParser = Jwts.parserBuilder().setSigningKey(this.key).build();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String createToken(String username, List<UserRole> userRoles, Long tokenExpiration) {
        Date now = new Date();
        Date validUntil = new Date(now.getTime() + tokenExpiration);

        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validUntil)
                .signWith(SignatureAlgorithm.HS256, key);

        if (userRoles != null) {
            builder.claim("auth", userRoles.stream()
                    .map(UserRole::getAuthority)
                    .collect(toList())
            );
        }

        return builder.compact();
    }

    public Tokens createTokens(String username, List<UserRole> userRoles) {
        String accessToken = createToken(username, userRoles, accessExpiration);
        String refreshToken = createToken(username, null, refreshExpiration);
        return new Tokens(accessToken, refreshToken);
    }

}
