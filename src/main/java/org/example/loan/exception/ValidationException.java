package org.example.loan.exception;

import lombok.Getter;
import org.example.loan.model.ValidationErrors;
import org.example.loan.model.ValidationError;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


@Getter
public class ValidationException extends Exception {

    private final ValidationErrors validationErrors = new ValidationErrors();

    public ValidationException(BindingResult bindingResult) {
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validationErrors.addError(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }
    }
}
