package com.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RoundValues {

    public static BigDecimal round(BigDecimal valueToRound) {
        return valueToRound.setScale(2, RoundingMode.HALF_UP);
    }
}
