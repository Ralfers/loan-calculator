package org.example.loan.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class BasePlanCalculationService {

    protected static final BigInteger TWELVE = BigInteger.valueOf(12);
    protected static final BigDecimal TWELVE_DECIMAL = new BigDecimal(TWELVE);

    public BigInteger toInteger(BigDecimal decimal) {
        return decimal
                .setScale(0, RoundingMode.HALF_EVEN)
                .toBigInteger();
    }
}
