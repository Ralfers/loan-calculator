package org.example.loan.service;

import org.example.loan.model.Installment;
import org.example.loan.model.LoanPlan;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

@Service
public class EMIPlanCalculationService extends BasePlanCalculationService {

    public LoanPlan calculateMonthlyPlan(BigInteger loanedAmount, BigDecimal interest, int loanMonths) {
        LoanPlan loanPlan = new LoanPlan()
                .setTotalPrincipalAmount(loanedAmount)
                .setInterest(interest)
                .setLoanMonths(loanMonths);

        fillInstallments(loanPlan, loanedAmount, interest, loanMonths);

        return loanPlan;
    }

    private void fillInstallments(LoanPlan loanPlan, BigInteger loanedAmount, BigDecimal interest, int loanMonths) {
        BigDecimal installmentAmount = calculateInstallmentAmount(loanedAmount, interest, loanMonths);
        BigDecimal monthlyInterest = interest.divide(TWELVE_DECIMAL, MathContext.DECIMAL128);

        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal totalInterestAmount = BigDecimal.ZERO;
        BigDecimal prevRemainingPrincipalAmount = new BigDecimal(loanedAmount);

        for (int i = 0; i < loanMonths; i++) {
            BigDecimal interestAmount = monthlyInterest.multiply(prevRemainingPrincipalAmount);
            BigDecimal principalAmount = installmentAmount.subtract(interestAmount);
            BigDecimal remainingPrincipalAmount = prevRemainingPrincipalAmount.subtract(principalAmount);

            Installment installment = new Installment()
                    .setAmount(toInteger(installmentAmount))
                    .setInterestAmount(toInteger(interestAmount))
                    .setPrincipalAmount(toInteger(principalAmount))
                    .setRemainingPrincipalAmount(toInteger(remainingPrincipalAmount));
            loanPlan.addInstallment(installment);

            totalAmount = totalAmount.add(installmentAmount);
            totalInterestAmount = totalInterestAmount.add(interestAmount);
            prevRemainingPrincipalAmount = remainingPrincipalAmount;
        }

        loanPlan.setTotalAmount(toInteger(totalAmount));
        loanPlan.setTotalInterestAmount(toInteger(totalInterestAmount));
    }

    private BigDecimal calculateInstallmentAmount(BigInteger loanedAmount, BigDecimal interest, int loanMonths) {
        BigDecimal monthlyInterest = interest.divide(TWELVE_DECIMAL, MathContext.DECIMAL128);

        return new BigDecimal(loanedAmount)
                .multiply(monthlyInterest)
                .multiply(monthlyInterest.add(BigDecimal.ONE).pow(loanMonths))
                .divide(
                        monthlyInterest.add(BigDecimal.ONE).pow(loanMonths)
                                .subtract(BigDecimal.ONE),
                        MathContext.DECIMAL128
                );
    }
}
