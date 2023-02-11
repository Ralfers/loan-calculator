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
public class LoanPlan {

    private Integer loanMonths;

    private String interest;

    private BigInteger totalAmount;

    private BigInteger totalPrincipalAmount;

    private BigInteger totalInterestAmount;

    private List<Installment> installments = new ArrayList<>();

    public LoanPlan addInstallment(Installment installment) {
        installments.add(installment);
        return this;
    }

    public LoanPlan setInterest(BigDecimal interest) {
        this.interest = interest.toString();
        return this;
    }

    @Override
    public String toString() {
        return String.format("months=%s, interest=%s, principal=%s, interest amount=%s, total amount=%s",
                loanMonths, interest, totalPrincipalAmount, totalInterestAmount, totalAmount
        );
    }
}
