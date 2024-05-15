package com.skillstorm.taxservice.exceptions;

public class NotFoundException extends RuntimeException {
  public NotFoundException() {
    super();
  }

  public NotFoundException(String message) {
    super(message);
  }
}
