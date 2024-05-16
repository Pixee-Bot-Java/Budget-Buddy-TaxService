package com.skillstorm.taxservice.exceptions;

import java.io.IOException;

public class UndeterminedContentException extends IOException {
    public UndeterminedContentException(String s) {
        super(s);
    }
}
