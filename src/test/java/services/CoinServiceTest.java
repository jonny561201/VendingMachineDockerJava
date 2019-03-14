package services;

import com.models.Coin;
import com.services.CoinService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.models.Coin.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CoinServiceTest {

    private CoinService coinService;

    @Before
    public void Setup() {
        coinService = new CoinService();
    }

    @Test
    public void isValidCoin_ShouldReturnFalseWhenCoinHasInvalidHeight() {
        Coin invalidWeight = new Coin(DOLLAR.diameter.doubleValue(), 99,99);

        boolean actual = coinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnFalseWhenCoinHasInvalidWeight() {
        Coin invalidWeight = new Coin(99.0, DOLLAR.weight.doubleValue(), 99.0);

        boolean actual = coinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinIsDollar() {
        Coin dollar = new Coin(DOLLAR.diameter.doubleValue(), DOLLAR.weight.doubleValue(), DOLLAR.value.doubleValue());
        boolean actual = coinService.isValidCoin(dollar);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTruehWhenCoinIsQuarter() {
        Coin quarter = new Coin(QUARTER.diameter.doubleValue(), QUARTER.weight.doubleValue(), QUARTER.value.doubleValue());
        boolean actual = coinService.isValidCoin(quarter);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTruehWhenCoinIsDime() {
        Coin dime = new Coin(DIME.diameter.doubleValue(), DIME.weight.doubleValue(), DIME.value.doubleValue());
        boolean actual = coinService.isValidCoin(dime);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinIsNickel() {
        Coin nickel = new Coin(NICKEL.diameter.doubleValue(), NICKEL.weight.doubleValue(), NICKEL.value.doubleValue());
        boolean actual = coinService.isValidCoin(nickel);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinWeightIsWithinMarginOfError() {
        Coin nickel = new Coin(NICKEL.diameter.doubleValue(), 21.206, NICKEL.value.doubleValue());
        boolean actual = coinService.isValidCoin(nickel);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinDiameterIsWithinMarginOfError() {
        Coin nickel = new Coin(4.995, NICKEL.weight.doubleValue(), NICKEL.value.doubleValue());
        boolean actual = coinService.isValidCoin(nickel);

        assertTrue(actual);
    }

    @Test
    public void countChange_ShouldReturnValueForSingleCoin() {
        List<Coin> coins = Collections.singletonList(NICKEL);

        BigDecimal actual = coinService.countChange(coins);

        assertEquals(NICKEL.value, actual);
    }

    @Test
    public void countChange_ShouldReturnValueForMultipleCoins() {
        List<Coin> coins = Arrays.asList(QUARTER, DIME);

        BigDecimal actual = coinService.countChange(coins);

        BigDecimal expected = QUARTER.value.add(DIME.value);
        assertEquals(expected, actual);
    }

    @Test
    public void countChange_ShouldReturnZeroWhenListEmpty() {
        List<Coin> coins = new ArrayList<>();

        BigDecimal actual = coinService.countChange(coins);

        assertEquals(BigDecimal.ZERO, actual);
    }

    @Test
    public void returnCorrectChange_ShouldReturnASingleCoin() {
        BigDecimal productCost = new BigDecimal(1.00);
        BigDecimal funds = new BigDecimal(1.05);

        List<Coin> actual = coinService.returnChange(productCost, funds);

        List<Coin> expectedCoins = Collections.singletonList(NICKEL);
        assertEquals(expectedCoins, actual);
    }

    @Test
    public void returnCorrectChange_ShouldReturnAMultipleCoins() {
        BigDecimal productCost = new BigDecimal(1.00);
        BigDecimal funds = new BigDecimal(1.40);

        List<Coin> actual = coinService.returnChange(productCost, funds);

        List<Coin> expectedCoins = Arrays.asList(QUARTER, DIME, NICKEL);
        assertEquals(expectedCoins, actual);
    }

    @Test
    public void returnCorrectChange_ShouldReturnSingleExactCoin() {
        List<Coin> actual = coinService.returnChange(BigDecimal.ZERO, QUARTER.value);

        List<Coin> expectedCoins = Collections.singletonList(QUARTER);
        assertEquals(expectedCoins, actual);
    }

    @Test
    public void returnCorrectChange_ShouldReturnAMultipleCoinsOfDuplicateDenominations() {
        BigDecimal productCost = new BigDecimal(1.00);
        BigDecimal funds = new BigDecimal(1.75);

        List<Coin> actual = coinService.returnChange(productCost, funds);

        List<Coin> expectedCoins = Arrays.asList(QUARTER, QUARTER, QUARTER);
        assertEquals(expectedCoins, actual);
    }
}
