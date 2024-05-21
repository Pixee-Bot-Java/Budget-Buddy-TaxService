package com.skillstorm.taxservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle NotFoundException from querying dbs for resources that do not exist:
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // Handle Bad Requests from trying to add or update entities with invalid data in the RequestBody:
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // Handle UnableToReadStreamException from errors reading from InputStream:
    @ExceptionHandler(UnableToReadStreamException.class)
    public ResponseEntity<String> handleUnableToReadStreamException(UnableToReadStreamException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // Handle UndeterminedContentException from errors reading from InputStream:
    @ExceptionHandler(UndeterminedContentException.class)
    public ResponseEntity<String> handleUndeterminedContentException(UndeterminedContentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    // Handle IllegalAccessError from trying to sum other income fields
    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<String> handleIllegalAccessError(IllegalAccessException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
  }
}
