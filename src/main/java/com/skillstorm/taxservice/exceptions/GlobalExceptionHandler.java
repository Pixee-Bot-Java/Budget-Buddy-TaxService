package com.skillstorm.taxservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle NotFoundException from querying dbs for resources that do not exist:
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
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
}
