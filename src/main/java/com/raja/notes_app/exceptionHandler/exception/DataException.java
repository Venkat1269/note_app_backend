package com.raja.notes_app.exceptionHandler.exception;

public class DataException extends RuntimeException {

    public DataException(String message) {
        super(message);
    }
    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
