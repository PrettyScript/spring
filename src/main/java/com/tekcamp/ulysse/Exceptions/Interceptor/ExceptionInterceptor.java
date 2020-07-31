package com.tekcamp.ulysse.Exceptions.Interceptor;

import com.tekcamp.ulysse.Exceptions.NotFoundException;
import com.tekcamp.ulysse.Exceptions.RecordExistsException;
import com.tekcamp.ulysse.Exceptions.Response.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> handleNoRecordFoundException(NotFoundException ex) {

        ErrorMessage errorResponse = new ErrorMessage(ex.getMessage());
        errorResponse.setMessage("No Record Found");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ResponseBody
    public ResponseEntity<Object> handleRecordExistsException(RecordExistsException ex) {

        ErrorMessage errorResponse = new ErrorMessage(ex.getMessage());
        errorResponse.setMessage("This account exists already");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
