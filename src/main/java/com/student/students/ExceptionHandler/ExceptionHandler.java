package com.student.students.ExceptionHandler;

import com.student.students.ErrorDetails.ErrorDetails;
import com.student.students.ResourceNotFound.ResourceNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFound(ResourceNotFound e, WebRequest request)
    {
        ErrorDetails errorDetails=new ErrorDetails(e.getMessage(),new Date(),request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

    }

@org.springframework.web.bind.annotation.ExceptionHandler
public ResponseEntity<ErrorDetails> ExceptionHandle(Exception e, WebRequest request)
{
    ErrorDetails errorDetails=new ErrorDetails(e.getMessage(),new Date(),request.getDescription(true));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);

}



}
