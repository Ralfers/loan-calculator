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
            if ("loanedAmount".equals(fieldError.getField())) {
                validationErrors.addError(new ValidationError(fieldError.getField(), ""));
            } else {
                validationErrors.addError(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
            }
        }
    }
}
