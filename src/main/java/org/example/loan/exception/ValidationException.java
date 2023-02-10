package org.example.loan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.loan.model.LoanCalculationError;
import org.springframework.validation.BindingResult;

@Getter
@Setter
@AllArgsConstructor
public class ValidationException extends Exception {

    private final BindingResult bindingResult;

    public LoanCalculationError toError() {
        return new LoanCalculationError();
    }
}
