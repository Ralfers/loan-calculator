package org.example.loan.controller;

import jakarta.validation.Valid;
import org.example.loan.exception.ValidationException;
import org.example.loan.model.LoanPlan;
import org.example.loan.model.LoanPlanCriteria;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.loan.service.LoanPlanService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
@Slf4j
@RequiredArgsConstructor
public class LoanPlanController {

    private final LoanPlanService loanPlanService;

    @GetMapping(path = "/housing/plan", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoanPlan calculateHousingLoanPlan(@Valid LoanPlanCriteria loanPlanCriteria, BindingResult bindingResult) throws Exception {
        log.debug("Calculating housing loan plan for amount {} and months {}", loanPlanCriteria.getLoanedAmount(), loanPlanCriteria.getLoanMonths());
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        LoanPlan loanPlan = loanPlanService.calculateHousingLoanPlan(loanPlanCriteria);

        log.debug("Calculated housing loan plan: {}", loanPlan);
        return loanPlan;
    }
}
