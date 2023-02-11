package org.example.loan.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@AllArgsConstructor
public class LoanPlanCriteria {

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private BigInteger loanedAmount;

    @NotNull
    @DecimalMin(value = "0", inclusive = false)
    private Integer loanMonths;
}
