package org.example.loan;

import org.example.loan.model.Installment;

import java.math.BigInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BasePlanCalculationServiceTest {

    protected void assertInstallment(Installment installment, int expectedAmount, int expectedPrincipalAmount,
                                     int expectedInterestAmount, int expectedRemainingPrincipalAmount) {
        assertThat(installment.getAmount(), equalTo(BigInteger.valueOf(expectedAmount)));
        assertThat(installment.getPrincipalAmount(), equalTo(BigInteger.valueOf(expectedPrincipalAmount)));
        assertThat(installment.getInterestAmount(), equalTo(BigInteger.valueOf(expectedInterestAmount)));
        assertThat(installment.getRemainingPrincipalAmount(), equalTo(BigInteger.valueOf(expectedRemainingPrincipalAmount)));
    }
}
