package org.example.loan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Getter
@Setter
@Accessors(chain = true)
public class Installment {

    private BigInteger amount;

    private BigInteger principalAmount;

    private BigInteger interestAmount;

    private BigInteger remainingPrincipalAmount;
}
