package com.skillstorm.taxservice.exceptions;

import java.io.IOException;

public class UnableToReadStreamException extends IOException {
    public UnableToReadStreamException(String s) {
        super(s);
    }
}
