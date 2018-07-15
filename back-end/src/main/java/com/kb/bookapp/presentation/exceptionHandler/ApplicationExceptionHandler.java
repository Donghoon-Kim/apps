package com.kb.bookapp.presentation.exceptionHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.security.PublicKey;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    private static final String ERROR_REFERENCE_URL = "";

    @ExceptionHandler(value = {Exception.class})
    protected Object handleCommonRuntime(Exception ex, WebRequest request) {
        return createResponseEntity(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, MethodArgumentTypeMismatchException.class})
    protected Object handleBadRequest(Exception ex, WebRequest request) {
        return createResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity createResponseEntity(Exception exception, HttpStatus httpStatus) {
        RestErrorResponseEntity responseEntity = new RestErrorResponseEntity();

        responseEntity.setCode(String.valueOf(exception.hashCode()));
        responseEntity.setMessage(exception.getMessage());
        responseEntity.setStatus(httpStatus.toString());
        responseEntity.setMoreInfo(ERROR_REFERENCE_URL);

        return new ResponseEntity<>(responseEntity, httpStatus);
    }

}
