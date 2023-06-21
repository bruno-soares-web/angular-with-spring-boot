package com.bruno.helpdesk.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;
    private List<FieldMessageError> error = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timeStemp, Integer status, String error, String message, String path) {
        super(timeStemp, status, error, message, path);
    }

    public List<FieldMessageError> getErrors() {
        return error;
    }

    public void addError(String fieldName, String message) {
        this.error.add(new FieldMessageError(fieldName, message));
    }
}
