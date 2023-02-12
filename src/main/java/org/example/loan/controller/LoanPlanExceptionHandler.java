package org.example.loan.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.loan.exception.ValidationException;
import org.example.loan.model.ValidationErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class LoanPlanExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ValidationErrors> handleValidationException(ValidationException validationException) {

        return new ResponseEntity<>(validationException.getValidationErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception exception) {

        return ResponseEntity.internalServerError().build();
    }
}
