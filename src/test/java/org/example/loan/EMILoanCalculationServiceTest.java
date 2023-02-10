package org.example.loan;

import lombok.RequiredArgsConstructor;
import org.example.loan.model.PaybackPlan;
import org.example.loan.service.EMIPlanCalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EMILoanCalculationServiceTest {

    @Autowired
    private EMIPlanCalculationService emiPlanCalculationService;

    @Test
    public void testCorrectEMIPaybackPlanGenerated() {
        // given
        BigInteger loanAmount = BigInteger.valueOf(20000);
        BigDecimal interest = new BigDecimal("0.035");
        int loanMonths = 120;

        // when
        PaybackPlan paybackPlan = emiPlanCalculationService.calculateMonthlyPlan(loanAmount, interest, loanMonths);

        // then
        assertThat(paybackPlan.getLoanMonths(), equalTo(loanMonths));
        assertThat(paybackPlan.getInterest(), equalTo("0.035"));
        assertThat(paybackPlan.getTotalPrincipalAmount(), equalTo(loanAmount));
        assertThat(paybackPlan.getTotalInterestAmount(), equalTo(BigInteger.valueOf(3733)));
        assertThat(paybackPlan.getTotalAmount(), equalTo(BigInteger.valueOf(23733)));
    }
}
