package org.raddan.newspaper.exception;

import org.raddan.newspaper.annotation.NotEmpty;
import org.raddan.newspaper.exception.custom.FieldEmptyException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.time.ZonedDateTime;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.*;

/**
 * @author Alexander Dudkin
 */
@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private record ExceptionDetails(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {  }

    @ExceptionHandler(value = {IllegalAccessException.class, InvocationTargetException.class})
    public ResponseEntity<?> handleInvocationException(RuntimeException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                BAD_REQUEST,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exceptionDetails, BAD_REQUEST);
    }

    @ExceptionHandler(value = {UnsupportedOperationException.class})
    public ResponseEntity<?> handleUnsupportedOperationException(UnsupportedOperationException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                METHOD_NOT_ALLOWED,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exceptionDetails, METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                NOT_FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exceptionDetails, NOT_FOUND);
    }

    @ExceptionHandler(value = {FieldEmptyException.class})
    public ResponseEntity<?> handleFieldEmptyException(FieldEmptyException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                BAD_REQUEST,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(exceptionDetails, BAD_REQUEST);
    }
}
