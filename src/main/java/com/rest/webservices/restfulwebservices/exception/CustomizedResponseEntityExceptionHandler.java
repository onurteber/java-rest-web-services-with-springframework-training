package com.rest.webservices.restfulwebservices.exception;

import com.rest.webservices.restfulwebservices.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        ExceptsionResponse exceptsionResponse = new ExceptsionResponse(new Date(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptsionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        ExceptsionResponse exceptsionResponse = new ExceptsionResponse(new Date(),ex.getMessage(), request.getDescription(false));
        return new ResponseEntity(exceptsionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptsionResponse exceptsionResponse = new ExceptsionResponse(new Date(),"Validation Failed", ex.getBindingResult().toString());
        return new ResponseEntity(exceptsionResponse, HttpStatus.BAD_REQUEST);
    }


}
