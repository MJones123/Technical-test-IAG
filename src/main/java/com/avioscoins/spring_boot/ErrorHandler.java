package com.avioscoins.spring_boot;

import java.security.InvalidParameterException;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    // Both Parameter and Path are specifying resources so error being returned is Not Found
    @ExceptionHandler(InvalidParameterException.class)
    public ErrorResponseException handleParameterError(HttpServletRequest req, InvalidParameterException err) {
        ErrorResponseException errResponse = new ErrorResponseException(HttpStatus.NOT_FOUND, err);
        errResponse.setDetail(err.getMessage());
        return errResponse;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponseException handleNoElementError(HttpServletRequest req, NoSuchElementException err) {
        ErrorResponseException errResponse = new ErrorResponseException(HttpStatus.NOT_FOUND, err);
        errResponse.setDetail(err.getMessage());
        return errResponse;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponseException handlePathError(HttpServletRequest req, IllegalArgumentException err) {
        ErrorResponseException errResponse = new ErrorResponseException(HttpStatus.NOT_FOUND, err);
        errResponse.setDetail(err.getMessage());
        return errResponse;
    }
}
