package com.project.chatapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestController
public class GlobleException {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorDetail> UserExceptionHandler(UserException e , WebRequest req) {

        ErrorDetail err = new ErrorDetail(e.getMessage() , req.getDescription(false), LocalDateTime.now());
        return  new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ErrorDetail> MessageExceptionHandler(MessageException ue , WebRequest req) {

        ErrorDetail err = new ErrorDetail(ue.getMessage() , req.getDescription(false), LocalDateTime.now());
        return  new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ChatException.class)
    public ResponseEntity<ErrorDetail> ChatExceptionHandler(ChatException ue , WebRequest req) {

        ErrorDetail err = new ErrorDetail(ue.getMessage() , req.getDescription(false), LocalDateTime.now());
        return  new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ErrorDetail> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException me) {

        String error = me.getBindingResult().getFieldError().getDefaultMessage();


        ErrorDetail err = new ErrorDetail("Validation" , error, LocalDateTime.now());
        return  new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetail> handleNoHandleFoundException(NoHandlerFoundException ex , WebRequest req) {

        ErrorDetail err = new ErrorDetail( "Enpoint not found" ,ex.getMessage() , LocalDateTime.now());
        return  new ResponseEntity<ErrorDetail>(err, HttpStatus.NOT_FOUND);

    }




    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> otherExceptionHandler(Exception e , WebRequest req) {

        ErrorDetail err = new ErrorDetail(e.getMessage() , req.getDescription(false), LocalDateTime.now());
        return  new ResponseEntity<ErrorDetail>(err, HttpStatus.BAD_REQUEST);

    }
}
