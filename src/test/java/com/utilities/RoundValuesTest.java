package com.utilities;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RoundValuesTest {

    @Test
    public void round_ShouldRoundValueToTwoDecimals() {
        var valueToRound = new BigDecimal("4.11000008");
        var actual = RoundValues.round(valueToRound);
        
        var expectedValue = new BigDecimal("4.11");
        assertEquals(expectedValue, actual);
    }

}