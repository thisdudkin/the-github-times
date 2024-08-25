package org.earlspilner.users.rest.advice;

import jakarta.persistence.EntityNotFoundException;
import org.earlspilner.users.rest.advice.custom.CustomException;
import org.earlspilner.users.rest.advice.custom.ProfileNotFoundException;
import org.earlspilner.users.rest.advice.custom.ProfileServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

/**
 * @author Alexander Dudkin
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorInfo errorInfo = new ErrorInfo(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorInfo> handleCustomException(CustomException ex) {
        ErrorInfo errorInfo = new ErrorInfo(ex);
        return ResponseEntity.status(ex.getHttpStatus()).body(errorInfo);
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleNotFoundException(ProfileNotFoundException ex) {
        ErrorInfo errorInfo = new ErrorInfo(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }

    @ExceptionHandler(ProfileServiceException.class)
    public ResponseEntity<ErrorInfo> handleServiceException(ProfileServiceException ex) {
        ErrorInfo errorInfo = new ErrorInfo(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleGeneralException(Exception ex) {
        ErrorInfo errorInfo = new ErrorInfo(ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
    }

    public record ErrorInfo(String className, String message, ZonedDateTime timestamp) {
        public ErrorInfo(Exception ex) {
            this(ex.getClass().getName(), ex.getLocalizedMessage(), ZonedDateTime.now());
        }
    }

}
