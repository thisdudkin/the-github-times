package org.earlspilner.users.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.earlspilner.users.rest.advice.custom.CustomException;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author Alexander Dudkin
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JwtTokenFilter(final JwtTokenProvider tokenProvider) {
        this.jwtTokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (AuthenticationException | AccessDeniedException ex) {
            SecurityContextHolder.clearContext();
            handleException(response, ex);
        } catch (CustomException e) {
            SecurityContextHolder.clearContext();
            handleCustomException(response, e);
        }
    }

    private void handleException(HttpServletResponse response, Exception ex) throws IOException {
        int status = (ex instanceof AuthenticationException) ? HttpServletResponse.SC_UNAUTHORIZED : HttpServletResponse.SC_FORBIDDEN;
        ErrorInfo errorInfo = new ErrorInfo(ex);
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
    }

    private void handleCustomException(HttpServletResponse response, CustomException ex) throws IOException {
        ErrorInfo errorInfo = new ErrorInfo(ex);
        response.setStatus(ex.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(errorInfo));
    }

    private record ErrorInfo(String className, String message) {
        public ErrorInfo(Exception ex) {
            this(ex.getClass().getName(), ex.getLocalizedMessage());
        }
    }

}
