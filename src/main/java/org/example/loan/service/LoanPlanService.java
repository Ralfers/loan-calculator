package org.example.loan.service;

import lombok.RequiredArgsConstructor;
import org.example.loan.model.LoanPlan;
import org.example.loan.model.LoanPlanCriteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LoanPlanService {

    private static final BigDecimal HOUSING_PLAN_INTEREST = new BigDecimal("0.035");

    private final EMIPlanCalculationService emiPlanCalculationService;

    public LoanPlan calculateHousingLoanPlan(LoanPlanCriteria loanPlanCriteria) {

        return emiPlanCalculationService.calculateMonthlyPlan(loanPlanCriteria.getLoanedAmount(), HOUSING_PLAN_INTEREST, loanPlanCriteria.getLoanMonths());
    }
}
