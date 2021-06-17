package com.github.kodomo.dsmpayments.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    protected ResponseEntity<ErrorResponse> handleMunchkinException(final GlobalException e) {
        final int statusCode = e.getStatusCode();
        final String message = e.getMessage();
        return new ResponseEntity<>(new ErrorResponse(statusCode, message), HttpStatus.valueOf(statusCode));
    }

}
