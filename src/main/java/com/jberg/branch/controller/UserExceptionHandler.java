package com.jberg.branch.controller;

import com.jberg.branch.model.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<UserErrorResponse> handleHttpClientError(HttpClientErrorException e) {

        UserErrorResponse errorResponse = new UserErrorResponse();
        errorResponse.setErrorMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<UserErrorResponse> handleMissingParameterException(MissingServletRequestParameterException e) {

        UserErrorResponse errorResponse = new UserErrorResponse();
        errorResponse.setErrorMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserErrorResponse> handleGeneralException(Exception e) {

        UserErrorResponse errorResponse = new UserErrorResponse();
        errorResponse.setErrorMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
