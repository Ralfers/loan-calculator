package org.example.loan;

import org.example.loan.model.Installment;
import org.example.loan.model.LoanPlan;
import org.example.loan.service.EMIPlanCalculationService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoanPlanControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EMIPlanCalculationService emiPlanCalculationService;

    private static Stream<Arguments> invalidLoanCriteriaSource() {
        return Stream.of(
                Arguments.of("test", "test"),
                Arguments.of("-1", "-1"),
                Arguments.of("0", "0")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidLoanCriteriaSource")
    public void testLoanCriteriaIsValidated(String loanedAmount, String loanMonths) throws Exception {
        // when - then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/loan/housing/plan")
                .queryParam("loanedAmount", loanedAmount)
                .queryParam("loanMonths", loanMonths))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[*].field", containsInAnyOrder("loanedAmount", "loanMonths")))
                .andReturn();
    }

    @Test
    public void testHousingLoanPlanEndpoint() throws Exception {
        // given
        BigInteger loanedAmount = BigInteger.valueOf(2000000);
        BigDecimal housingInterest = new BigDecimal("0.035");
        int loanMonths = 120;

        Mockito.when(emiPlanCalculationService.calculateMonthlyPlan(loanedAmount, housingInterest, loanMonths))
                        .thenReturn(generateTestLoanPlan(loanedAmount, housingInterest, loanMonths));

        // when - then
        mockMvc.perform(MockMvcRequestBuilders.get("/loan/housing/plan")
                .queryParam("loanedAmount", loanedAmount.toString())
                .queryParam("loanMonths", Integer.toString(loanMonths)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(validLoanPlanData(loanedAmount, housingInterest, loanMonths))
                .andReturn();
    }

    private LoanPlan generateTestLoanPlan(BigInteger loanedAmount, BigDecimal interest, int loanMonths) {
        return new LoanPlan()
                .setLoanMonths(loanMonths)
                .setInterest(interest)
                .setTotalPrincipalAmount(loanedAmount)
                .setTotalInterestAmount(BigInteger.valueOf(500))
                .setTotalAmount(BigInteger.valueOf(1000))
                .addInstallment(new Installment()
                        .setAmount(BigInteger.valueOf(100))
                        .setPrincipalAmount(BigInteger.valueOf(100))
                        .setInterestAmount(BigInteger.valueOf(100))
                        .setRemainingPrincipalAmount(BigInteger.valueOf(100)))
                .addInstallment(new Installment()
                        .setAmount(BigInteger.valueOf(200))
                        .setPrincipalAmount(BigInteger.valueOf(200))
                        .setInterestAmount(BigInteger.valueOf(200))
                        .setRemainingPrincipalAmount(BigInteger.valueOf(200))
                )
                .addInstallment(new Installment()
                        .setAmount(BigInteger.valueOf(300))
                        .setPrincipalAmount(BigInteger.valueOf(300))
                        .setInterestAmount(BigInteger.valueOf(300))
                        .setRemainingPrincipalAmount(BigInteger.valueOf(300))
                );
    }

    private ResultMatcher[] validLoanPlanData(BigInteger loanedAmount, BigDecimal interest, int loanMonths) {
        return new ResultMatcher[]{
                jsonPath("$.loanMonths", equalTo(loanMonths)),
                jsonPath("$.interest", equalTo(interest.toString())),
                jsonPath("$.totalPrincipalAmount", equalTo(loanedAmount.intValue())),
                jsonPath("$.totalInterestAmount", equalTo(500)),
                jsonPath("$.totalAmount", equalTo(1000)),
                jsonPath("$.installments[0].amount", equalTo(100)),
                jsonPath("$.installments[0].principalAmount", equalTo(100)),
                jsonPath("$.installments[0].interestAmount", equalTo(100)),
                jsonPath("$.installments[0].remainingPrincipalAmount", equalTo(100)),
                jsonPath("$.installments[1].amount", equalTo(200)),
                jsonPath("$.installments[1].principalAmount", equalTo(200)),
                jsonPath("$.installments[1].interestAmount", equalTo(200)),
                jsonPath("$.installments[1].remainingPrincipalAmount", equalTo(200)),
                jsonPath("$.installments[2].amount", equalTo(300)),
                jsonPath("$.installments[2].principalAmount", equalTo(300)),
                jsonPath("$.installments[2].interestAmount", equalTo(300)),
                jsonPath("$.installments[2].remainingPrincipalAmount", equalTo(300)),
        };
    }
}
