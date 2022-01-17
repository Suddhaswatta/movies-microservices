package com.suddha.movies.info.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ExceptionResponse> handleMovieDataInvalidException(WebExchangeBindException ex) {
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError moviesInfoConstraintViolation : ex.getBindingResult()
                .getFieldErrors()) {
            errorMap.put(moviesInfoConstraintViolation.getField(), moviesInfoConstraintViolation.getDefaultMessage());
        }
        return new ResponseEntity<>(new ExceptionResponse(errorMap), HttpStatus.BAD_REQUEST);
    }
}
