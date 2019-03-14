package com.utilities;

import java.math.BigDecimal;

public class RoundValues {

    public static BigDecimal round(BigDecimal valueToRound) {
        return valueToRound.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
