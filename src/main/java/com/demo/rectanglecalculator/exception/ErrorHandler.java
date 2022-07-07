package com.demo.rectanglecalculator.exception;

import com.demo.rectanglecalculator.model.ShapeApiError;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public final class ErrorHandler {

    public static final String SHAPE_API_PATH = "/api/v1/interactions";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ShapeApiError> handleConstraintViolationException(final MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(new ShapeApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.getMessage(),
                SHAPE_API_PATH),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JsonParseException.class)
    ResponseEntity<ShapeApiError> handleJsonParseException(final JsonParseException exception) {
        return new ResponseEntity<>(new ShapeApiError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.getMessage(),
                SHAPE_API_PATH),
                HttpStatus.BAD_REQUEST);
    }
}
