package org.raddan.newspaper.exception;

import jakarta.persistence.EntityNotFoundException;
import org.raddan.newspaper.exception.custom.AlreadyExistsException;
import org.raddan.newspaper.filter.DateFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private record ExceptionDetails(String message, HttpStatus status, String timestamp) { }

    @ExceptionHandler(value = {AlreadyExistsException.class, BadCredentialsException.class})
    public ResponseEntity<?> handleAlreadyExistsException(RuntimeException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                BAD_REQUEST,
                DateFilter.formatInstant(Instant.now().getEpochSecond())
        );

        return new ResponseEntity<>(exceptionDetails, BAD_REQUEST);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<?> handleUsernameNotFoundException(RuntimeException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                NOT_FOUND,
                DateFilter.formatInstant(Instant.now().getEpochSecond())
        );

        return new ResponseEntity<>(exceptionDetails, NOT_FOUND);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(
                ex.getMessage(),
                INTERNAL_SERVER_ERROR,
                DateFilter.formatInstant(Instant.now().getEpochSecond())
        );

        return new ResponseEntity<>(exceptionDetails, INTERNAL_SERVER_ERROR);
    }

}
