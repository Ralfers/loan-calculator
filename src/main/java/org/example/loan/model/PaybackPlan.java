package org.example.loan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PaybackPlan {

    private Integer loanMonths;

    private String interest;

    private BigInteger totalAmount;

    private BigInteger totalPrincipalAmount;

    private BigInteger totalInterestAmount;

    private List<Installment> installments = new ArrayList<>();

    public void addInstallment(Installment installment) {
        installments.add(installment);
    }

    public PaybackPlan setInterest(BigDecimal interest) {
        this.interest = interest.toString();
        return this;
    }
}
