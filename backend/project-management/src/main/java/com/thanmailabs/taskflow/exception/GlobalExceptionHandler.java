package com.thanmailabs.taskflow.exception;

import com.thanmailabs.taskflow.dto.response.ErrorResponse;
import com.thanmailabs.taskflow.dto.response.ValidationError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final String VALIDATION_FAILED = "Validation failed";
    private static final String STATUS_VALIDATION_FAILED = "Invalid value for status";
    private static final String INVALID_CREDENTIALS = "Invalid email or password";

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException ex,
                                                                 HttpServletRequest request) {
        ErrorResponse response = buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundExists(UserNotFoundException ex,
                                                                 HttpServletRequest request) {
        ErrorResponse response = buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundExists(TaskNotFoundException ex,
                                                                  HttpServletRequest request) {
        ErrorResponse response = buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(TaskVersionConflictException.class)
    public ResponseEntity<ErrorResponse> handleTaskVersionConflict(TaskVersionConflictException ex,
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

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex,
                                                                           HttpServletRequest request) {
        ErrorResponse response = buildErrorResponse(HttpStatus.UNAUTHORIZED, INVALID_CREDENTIALS, request);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpServletRequest request) {

        ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, STATUS_VALIDATION_FAILED, request);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
