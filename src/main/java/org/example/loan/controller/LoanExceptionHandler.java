package org.example.loan.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.loan.exception.ValidationException;
import org.example.loan.model.LoanCalculationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class LoanExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<LoanCalculationError> handleValidationException(ValidationException validationException) {
        log.error("Validation error occurred: {}", validationException.getBindingResult().getModel());

        return new ResponseEntity<>(validationException.toError(), HttpStatus.BAD_REQUEST);
    }
}
