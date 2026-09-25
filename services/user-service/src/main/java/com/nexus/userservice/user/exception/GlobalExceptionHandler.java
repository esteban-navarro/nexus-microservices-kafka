package com.nexus.userservice.user.exception;

import com.nexus.userservice.user.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleUserNotFound(
            UserNotFoundException exception) {

        ApiResponse<Void> error = new ApiResponse<>(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailAlreadyExists(
            EmailAlreadyExistsException exception) {

        ApiResponse<Void> error = new ApiResponse<>(
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

}