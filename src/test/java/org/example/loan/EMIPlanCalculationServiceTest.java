package org.example.loan;

import org.example.loan.model.Installment;
import org.example.loan.model.LoanPlan;
import org.example.loan.service.EMIPlanCalculationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EMIPlanCalculationServiceTest extends BasePlanCalculationServiceTest {

    @Autowired
    EMIPlanCalculationService emiPlanCalculationService;

    @Test
    public void testCorrectEMILoanPlanGenerated() {
        // given
        BigInteger loanAmount = BigInteger.valueOf(20000);
        BigDecimal interest = new BigDecimal("0.035");
        int loanMonths = 24;

        // when
        LoanPlan loanPlan = emiPlanCalculationService.calculateMonthlyPlan(loanAmount, interest, loanMonths);

        // then
        assertThat(loanPlan.getLoanMonths(), equalTo(loanMonths));
        assertThat(loanPlan.getInterest(), equalTo("0.035"));
        assertThat(loanPlan.getTotalPrincipalAmount(), equalTo(loanAmount));
        assertThat(loanPlan.getTotalInterestAmount(), equalTo(BigInteger.valueOf(737)));
        assertThat(loanPlan.getTotalAmount(), equalTo(BigInteger.valueOf(20737)));

        List<Installment> loanInstallments = loanPlan.getInstallments();
        assertThat(loanInstallments.size(), equalTo(24));

        assertInstallment(loanInstallments.get(0), 864, 806, 58, 19194);
        assertInstallment(loanInstallments.get(1), 864, 808, 56, 18386);
        assertInstallment(loanInstallments.get(2), 864, 810, 54, 17576);
        assertInstallment(loanInstallments.get(3), 864, 813, 51, 16763);
        assertInstallment(loanInstallments.get(4), 864, 815, 49, 15948);
        assertInstallment(loanInstallments.get(5), 864, 818, 47, 15130);
        assertInstallment(loanInstallments.get(6), 864, 820, 44, 14310);
        assertInstallment(loanInstallments.get(7), 864, 822, 42, 13488);
        assertInstallment(loanInstallments.get(8), 864, 825, 39, 12663);
        assertInstallment(loanInstallments.get(9), 864, 827, 37, 11836);
        assertInstallment(loanInstallments.get(10), 864, 830, 35, 11007);
        assertInstallment(loanInstallments.get(11), 864, 832, 32, 10175);
        assertInstallment(loanInstallments.get(12), 864, 834, 30, 9340);
        assertInstallment(loanInstallments.get(13), 864, 837, 27, 8504);
        assertInstallment(loanInstallments.get(14), 864, 839, 25, 7664);
        assertInstallment(loanInstallments.get(15), 864, 842, 22, 6823);
        assertInstallment(loanInstallments.get(16), 864, 844, 20, 5978);
        assertInstallment(loanInstallments.get(17), 864, 847, 17, 5132);
        assertInstallment(loanInstallments.get(18), 864, 849, 15, 4283);
        assertInstallment(loanInstallments.get(19), 864, 852, 12, 3431);
        assertInstallment(loanInstallments.get(20), 864, 854, 10, 2577);
        assertInstallment(loanInstallments.get(21), 864, 857, 8, 1721);
        assertInstallment(loanInstallments.get(22), 864, 859, 5, 862);
        assertInstallment(loanInstallments.get(23), 864, 862, 3, 0);
    }
}
