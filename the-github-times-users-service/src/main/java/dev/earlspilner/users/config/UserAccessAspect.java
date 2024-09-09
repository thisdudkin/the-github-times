package dev.earlspilner.users.config;

import dev.earlspilner.users.annotation.CheckUserAccess;
import dev.earlspilner.users.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author Alexander Dudkin
 */
@Aspect
@Component
@RequiredArgsConstructor
public class UserAccessAspect {

    private final JwtUtil jwtUtil;

    @Around("@annotation(checkUserAccess)")
    public Object checkUserAccess(ProceedingJoinPoint joinPoint, CheckUserAccess checkUserAccess) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String token = request.getHeader("Authorization").substring(7);
        String jwtUsername = jwtUtil.getUsernameFromToken(token);

        Object[] args = joinPoint.getArgs();
        String pathUsername = null;

        for (Object arg : args) {
            if (arg instanceof String) {
                pathUsername = (String) arg;
                break;
            }
        }

        if (!jwtUsername.equals(pathUsername)) {
            throw new AccessDeniedException("Access denied. You are not owning this resource!");
        }

        return joinPoint.proceed();
    }

}
