package org.example.loan.service;

import lombok.RequiredArgsConstructor;
import org.example.loan.model.LoanCriteria;
import org.example.loan.model.PaybackPlan;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LoanService {

    private static final BigDecimal HOUSING_PLAN_INTEREST = new BigDecimal("0.035");

    private final EMIPlanCalculationService emiLoanCalculationService;

    public PaybackPlan calculateHousingLoanPlan(LoanCriteria loanCriteria) {

        return emiLoanCalculationService.calculateMonthlyPlan(loanCriteria.getLoanedAmount(), HOUSING_PLAN_INTEREST, loanCriteria.getLoanMonths());
    }
}
