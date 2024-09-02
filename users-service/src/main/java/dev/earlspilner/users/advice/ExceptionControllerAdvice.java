package dev.earlspilner.users.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.OffsetDateTime;

import static java.time.OffsetDateTime.now;
import static org.springframework.http.HttpStatus.*;

/**
 * Could either use org.springframework.http.ProblemDetails or
 * custom record ProblemDetails
 *
 * @author Alexander Dudkin
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(FieldUpdateException.class)
    public ResponseEntity<ProblemDetail> handleFieldUpdateException(FieldUpdateException ex, WebRequest request) {
        return createProblemDetail(
                "Field Update Error",
                BAD_REQUEST.value(),
                ex.getLocalizedMessage(),
                request,
                now()
        );
    }

    @ExceptionHandler({UsernameAlreadyExistsException.class, EmailAlreadyExistsException.class})
    public ResponseEntity<ProblemDetail> handleUsernameAlreadyExistsException(Exception ex, WebRequest request) {
        return createProblemDetail(
                "Bad credentials",
                CONFLICT.value(),
                ex.getLocalizedMessage(),
                request,
                now()
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleUsernameNotFoundException(Exception ex, WebRequest request) {
        return createProblemDetail(
                "Username not found",
                NOT_FOUND.value(),
                ex.getLocalizedMessage(),
                request,
                now()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemDetail> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return createProblemDetail(
                "Something went wrong..",
                INTERNAL_SERVER_ERROR.value(),
                ex.getLocalizedMessage(),
                request,
                now()
        );
    }

    private ResponseEntity<ProblemDetail> createProblemDetail(String title, int status, String detail, WebRequest request, OffsetDateTime timestamp) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setInstance(URI.create(request.getDescription(false).substring(4)));
        problemDetail.setProperty("timestamp", timestamp);

        return new ResponseEntity<>(problemDetail, HttpStatus.valueOf(status));
    }

}
