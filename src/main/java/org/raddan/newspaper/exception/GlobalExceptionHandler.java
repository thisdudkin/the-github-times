package org.raddan.newspaper.exception;

import org.raddan.newspaper.exception.custom.*;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;

import static java.time.ZonedDateTime.now;
import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.*;

/**
 * @author Alexander Dudkin
 */
@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private record ExceptionDetails(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
    }

    @ExceptionHandler({
            ArticleAlreadyExistsException.class,
            CategoryAlreadyExistsException.class,
            ProfileAlreadyExistsException.class,
            TagAlreadyExistsException.class,
            UserAlreadyExistsWithThatEmailException.class,
            UserAlreadyExistsWithThatUsernameException.class
    })
    public ResponseEntity<?> handleAlreadyExistsExceptions(RuntimeException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                CONFLICT,
                now()
        );

        return new ResponseEntity<>(exceptionDetails, CONFLICT);
    }

    @ExceptionHandler({
            ArticleNotFoundException.class,
            CategoryNotFoundException.class,
            ProfileNotFoundException.class,
            TagNotFoundException.class,
            UsernameNotFoundException.class
    })
    public ResponseEntity<?> handleNotFoundExceptions(RuntimeException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                NOT_FOUND,
                now()
        );

        return new ResponseEntity<>(exceptionDetails, NOT_FOUND);
    }

    @ExceptionHandler({
            IllegalAccessException.class,
            InvocationTargetException.class
    })
    public ResponseEntity<?> handleInvocationException(RuntimeException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                BAD_REQUEST,
                now()
        );

        return new ResponseEntity<>(exceptionDetails, BAD_REQUEST);
    }

    @ExceptionHandler({
            UnsupportedOperationException.class
    })
    public ResponseEntity<?> handleUnsupportedOperationException(UnsupportedOperationException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                METHOD_NOT_ALLOWED,
                now()
        );

        return new ResponseEntity<>(exceptionDetails, METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({
            FieldEmptyException.class
    })
    public ResponseEntity<?> handleFieldEmptyException(FieldEmptyException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                BAD_REQUEST,
                now()
        );

        return new ResponseEntity<>(exceptionDetails, BAD_REQUEST);
    }

    @ExceptionHandler({
            UnauthorizedException.class
    })
    public ResponseEntity<?> handleUnauthorizedException(UnauthorizedException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                UNAUTHORIZED,
                now()
        );

        return new ResponseEntity<>(exceptionDetails, UNAUTHORIZED);
    }

}
