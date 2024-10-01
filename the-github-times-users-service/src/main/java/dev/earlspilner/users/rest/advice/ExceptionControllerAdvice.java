package dev.earlspilner.users.rest.advice;

import dev.earlspilner.users.rest.advice.custom.EmailAlreadyExistsException;
import dev.earlspilner.users.rest.advice.custom.UserNotFoundException;
import dev.earlspilner.users.rest.advice.custom.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * @author Alexander Dudkin
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Map<Class<? extends Throwable>, HttpStatus> exceptionStatusMap = new ConcurrentHashMap<>();

    static {
        exceptionStatusMap.put(AccessDeniedException.class, HttpStatus.FORBIDDEN);
        exceptionStatusMap.put(UsernameAlreadyExistsException.class, HttpStatus.CONFLICT);
        exceptionStatusMap.put(UserNotFoundException.class, HttpStatus.NOT_FOUND);
        exceptionStatusMap.put(EmailAlreadyExistsException.class, HttpStatus.CONFLICT);
        exceptionStatusMap.put(UsernameNotFoundException.class, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            UserNotFoundException.class,
            UsernameAlreadyExistsException.class,
            EmailAlreadyExistsException.class,
            UsernameNotFoundException.class
    })
    public ResponseEntity<ProblemDetail> handleException(Exception e) {
        HttpStatus status = exceptionStatusMap.getOrDefault(e.getClass(), INTERNAL_SERVER_ERROR);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        return ResponseEntity.status(status).body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; ", "Validation failed: ", ""));

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(BAD_REQUEST, errorMessage);
        return ResponseEntity.status(BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleCommonException(Exception e) {
        HttpStatus status = INTERNAL_SERVER_ERROR;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, e.getMessage());
        return ResponseEntity.status(status).body(problemDetail);
    }

}
