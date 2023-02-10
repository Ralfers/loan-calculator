package org.example.loan.service;

import org.example.loan.model.Installment;
import org.example.loan.model.PaybackPlan;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

@Service
public class EMIPlanCalculationService extends BasePlanCalculationService {

    public PaybackPlan calculateMonthlyPlan(BigInteger loanedAmount, BigDecimal interest, int loanMonths) {
        PaybackPlan paybackPlan = new PaybackPlan()
                .setTotalPrincipalAmount(loanedAmount)
                .setInterest(interest)
                .setLoanMonths(loanMonths);

        fillInstallments(paybackPlan, loanedAmount, interest, loanMonths);

        return paybackPlan;
    }

    private void fillInstallments(PaybackPlan paybackPlan, BigInteger loanedAmount, BigDecimal interest, int loanMonths) {
        BigDecimal installmentAmount = calculateInstallmentAmount(loanedAmount, interest, loanMonths);
        BigDecimal monthlyInterest = interest.divide(TWELVE_DECIMAL, MathContext.DECIMAL32);

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
            paybackPlan.addInstallment(installment);

            totalAmount = totalAmount.add(installmentAmount);
            totalInterestAmount = totalInterestAmount.add(interestAmount);
            prevRemainingPrincipalAmount = remainingPrincipalAmount;
        }

        paybackPlan.setTotalAmount(toInteger(totalAmount));
        paybackPlan.setTotalInterestAmount(toInteger(totalInterestAmount));
    }

    private BigDecimal calculateInstallmentAmount(BigInteger loanedAmount, BigDecimal interest, int loanMonths) {
        BigDecimal monthlyInterest = interest.divide(TWELVE_DECIMAL, MathContext.DECIMAL32);

        return new BigDecimal(loanedAmount)
                .multiply(monthlyInterest)
                .multiply(monthlyInterest.add(BigDecimal.ONE).pow(loanMonths))
                .divide(
                        monthlyInterest.add(BigDecimal.ONE).pow(loanMonths)
                                .subtract(BigDecimal.ONE),
                        MathContext.DECIMAL32
                );
    }
}
