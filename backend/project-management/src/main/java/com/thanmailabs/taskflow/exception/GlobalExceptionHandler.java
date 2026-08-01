package com.thanmailabs.taskflow.exception;

import com.thanmailabs.taskflow.dto.response.ErrorResponse;
import com.thanmailabs.taskflow.dto.response.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String VALIDATION_FAILED = "Validation failed";

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex,
                                                                 HttpServletRequest request) {
        ErrorResponse response = buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                      HttpServletRequest request) {
        List<ValidationError> errors =
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(fieldError ->
                                new ValidationError(
                                        fieldError.getField(),
                                        fieldError.getDefaultMessage()))
                        .toList();
        ErrorResponse response = buildErrorResponse(HttpStatus.BAD_REQUEST, VALIDATION_FAILED, request);
        response.setErrors(errors);
        return ResponseEntity.badRequest().body(response);
    }

    private ErrorResponse buildErrorResponse(
            HttpStatus status,
            String message,
            HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setStatus(status.value());
        response.setError(status.getReasonPhrase());
        response.setPath(request.getRequestURI());
        response.setMessage(message);
        return response;
    }
}
