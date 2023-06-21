package com.bruno.helpdesk.exceptions;

public class IntegrityViolationException extends RuntimeException{

    public IntegrityViolationException(String message) {
        super(message);
    }

    public IntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
