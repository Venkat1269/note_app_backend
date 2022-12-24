package com.raja.notes_app.exceptionHandler.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        this("User Not Present for the id " + id);
    }
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
