package dev.earlspilner.profiles.advice;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.OffsetDateTime.now;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Could either use org.springframework.http.ProblemDetails or
 * custom record ProblemDetails
 *
 * @author Alexander Dudkin
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleProfileNotFoundException(ProfileNotFoundException ex, WebRequest request) {
        return createProblemDetail(
                "Profile not found",
                NOT_FOUND.value(),
                ex.getLocalizedMessage(),
                request,
                now()
        );
    }

    @ExceptionHandler(FeignException.FeignClientException.class)
    public ResponseEntity<ProblemDetail> handleFeignClientException(FeignException ex, WebRequest request) {
        return createProblemDetail(
                "Bad credentials",
                NOT_FOUND.value(),
                extractRelevantFeignMessage(ex.getMessage()),
                request,
                now()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ProblemDetail> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return createProblemDetail(
                "Unexpected error",
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

    private String extractRelevantFeignMessage(String message) {
        Pattern pattern = Pattern.compile("\\[(\\d{3})] during \\[(GET|POST|PUT|DELETE|PATCH)] to \\[.*?\\]");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return "Feign client error occurred.";
    }

}
