package org.example.loan.controller;

import jakarta.validation.Valid;
import org.example.loan.exception.ValidationException;
import org.example.loan.model.LoanCriteria;
import org.example.loan.model.PaybackPlan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.loan.service.LoanService;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
@Slf4j
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @GetMapping(path = "/housing/plan", produces = MediaType.APPLICATION_JSON_VALUE)
    public PaybackPlan calculateHousingPlan(@Valid LoanCriteria loanCriteria, BindingResult bindingResult) throws Exception {
        log.debug("Calculating housing loan for amount {} and months {}", loanCriteria.getLoanedAmount(), loanCriteria.getLoanMonths());
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        return loanService.calculateHousingLoanPlan(loanCriteria);
    }
}
