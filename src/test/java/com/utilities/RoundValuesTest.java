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

    @Test
    public void round_ShouldRoundValueBelowFiveDown() {
        var valueToRound = new BigDecimal("3.4444444");
        var actual = RoundValues.round(valueToRound);

        var expectedValue = new BigDecimal("3.44");
        assertEquals(expectedValue, actual);
    }

    @Test
    public void round_ShouldRoundValueAboveFiveUp() {
        var valueToRound = new BigDecimal("3.446666");
        var actual = RoundValues.round(valueToRound);

        var expectedValue = new BigDecimal("3.45");
        assertEquals(expectedValue, actual);
    }

}