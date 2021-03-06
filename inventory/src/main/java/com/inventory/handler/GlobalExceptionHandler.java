package com.inventory.handler;

import com.inventory.exception.IllegalRequestBodyException;
import com.inventory.exception.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorInformation handle(final NoHandlerFoundException e) {
        return new ErrorInformation
                .Builder("SVR_URI_002", "an invalid url was specified for path of URI.").build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInformation handle(final MethodArgumentTypeMismatchException e) {
        return new ErrorInformation
                .Builder("SVR_URI_001", "an invalid parameter was specified for path of URI.").build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.OK)
    public ErrorInformation handle(final ItemNotFoundException e) {
        return new ErrorInformation.Builder("SVR_URI_010", "could not find specified item in the inventory service.").build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorInformation handle(final IllegalRequestBodyException e) {
        return new ErrorInformation.Builder("SVR_REQUEST_001", e.getMessage()).build();
    }

}
