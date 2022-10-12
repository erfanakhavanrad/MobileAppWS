package com.example.mobileappws.exceptions;

import com.example.mobileappws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException userServiceException, WebRequest webRequest) {
//        System.out.println("Triggered here");
        ErrorMessage errormessage = new ErrorMessage(new Date(), userServiceException.getMessage());
        return new ResponseEntity<>(errormessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(Exception exception, WebRequest webRequest) {
//        System.out.println("Triggered here");
        ErrorMessage errormessage = new ErrorMessage(new Date(), exception.getMessage());
        return new ResponseEntity<>(errormessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
