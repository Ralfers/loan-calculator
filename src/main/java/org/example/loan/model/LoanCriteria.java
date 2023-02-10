package org.example.loan.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@RequiredArgsConstructor
@Getter
@Setter
public class LoanCriteria {

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigInteger loanedAmount;

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Integer loanMonths;
}
