package com.raja.notes_app.exceptionHandler;

import com.raja.notes_app.exceptionHandler.exception.DataException;
import com.raja.notes_app.exceptionHandler.exception.NotFoundException;
import com.raja.notes_app.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {DataException.class})
    public ResponseEntity<ExceptionResponse> handleException(DataException dataException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(dataException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException notFoundException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(notFoundException.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
