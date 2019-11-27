package com.models;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    @Test
    public void getCost_shouldDefaultValueToZero() {
        var actual = new Product();
        assertEquals(BigDecimal.ZERO, actual.getCost());
    }
}