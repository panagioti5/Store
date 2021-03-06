package com.app.store.exceptions;
import com.app.store.infra.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleAllExceptions(ConstraintViolationException e, WebRequest request) {
        StringBuilder sbIssues = new StringBuilder();
        e.getConstraintViolations().forEach(x -> sbIssues.append(x.getMessage()).append(";"));
        ExceptionResponse exceptionResponse = new ExceptionResponse(sbIssues.toString(), ErrorCode.GENERIC_ERROR.code, new Date());
        request.getDescription(false);
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(CustomerNotFoundException e, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity(e.getResponse(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(ProductNotFoundException e, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity(e.getResponse(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<Object> handleAllExceptions(OrderNotFoundException e, WebRequest request) {
        request.getDescription(false);
        return new ResponseEntity(e.getResponse(), HttpStatus.NOT_FOUND);
    }

}