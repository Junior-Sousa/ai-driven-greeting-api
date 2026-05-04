package com.aidd.greeting.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Maps framework and validation exceptions to HTTP responses using
 * <strong>RFC 7807</strong> {@link ProblemDetail} bodies.
 *
 * <p>Field validation errors are attached under the extension property
 * {@code invalid_params} for machine-readable clients.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final URI VALIDATION_TYPE = URI.create("about:blank");

    /**
     * Handles request body validation failures (e.g. {@code @Valid} on controller parameters).
     *
     * @param ex      binding and validation errors
     * @param request current HTTP request (used for {@code instance})
     * @return 400 with {@code ProblemDetail} and {@code invalid_params} list
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "Request validation failed.");
        problem.setTitle("Bad Request");
        problem.setType(VALIDATION_TYPE);
        problem.setInstance(URI.create(request.getRequestURI()));

        List<Map<String, String>> violations = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(err -> {
            Map<String, String> entry = new LinkedHashMap<>();
            entry.put("field", err.getField());
            String msg = err.getDefaultMessage() != null ? err.getDefaultMessage() : "invalid";
            entry.put("message", msg);
            violations.add(entry);
        });
        problem.setProperty("invalid_params", violations);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }
}
