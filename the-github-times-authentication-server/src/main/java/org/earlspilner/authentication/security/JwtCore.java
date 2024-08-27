package org.earlspilner.authentication.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.earlspilner.authentication.rest.dto.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.util.stream.Collectors.toList;

/**
 * @author Alexander Dudkin
 */
@Service
public class JwtCore {

    @Value("${security.jwt.token.secret-key}")
    private String jwtSecret;

    @Value("${security.jwt.token.accessExpiration}")
    private Long accessExpiration;

    @Value("${security.jwt.token.refreshExpiration}")
    private Long refreshExpiration;

    private Key key;

    private JwtParser jwtParser;

    private final CustomUserDetails customUserDetails;

    @Autowired
    public JwtCore(CustomUserDetails customUserDetails) {
        this.customUserDetails = customUserDetails;
    }

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtSecret.getBytes());
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.jwtParser = Jwts.parserBuilder().setSigningKey(this.key).build();
    }

    public Tokens createTokens(String username, List<UserRole> userRoles) {
        String accessToken = createToken(username, userRoles, accessExpiration);
        String refreshToken = createToken(username, null, refreshExpiration);
        return new Tokens(accessToken, refreshToken);
    }

    public String createToken(String username, List<UserRole> userRoles, Long expiration) {
        Date now = new Date();
        Date validUntil = new Date(now.getTime() + expiration);

        JwtBuilder builder = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(validUntil)
                .signWith(key, HS256);

        if (userRoles != null) {
            builder.claim("auth", userRoles.stream()
                    .map(UserRole::getAuthority)
                    .collect(toList()));
        }

        return builder.compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // TODO
            throw new RuntimeException("Expired or invalid JWT token", e);
        }
    }

}
