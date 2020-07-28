package services;

import com.models.Coin;
import com.services.CoinService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
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
        var invalidWeight = new Coin(DOLLAR.diameter.doubleValue(), 99,99);
        var actual = CoinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnFalseWhenCoinHasInvalidWeight() {
        var invalidWeight = new Coin(99.0, DOLLAR.weight.doubleValue(), 99.0);
        var actual = CoinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinIsDollar() {
        var dollar = new Coin(DOLLAR.diameter.doubleValue(), DOLLAR.weight.doubleValue(), DOLLAR.value.doubleValue());
        var actual = CoinService.isValidCoin(dollar);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTruehWhenCoinIsQuarter() {
        var quarter = new Coin(QUARTER.diameter.doubleValue(), QUARTER.weight.doubleValue(), QUARTER.value.doubleValue());
        var actual = CoinService.isValidCoin(quarter);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTruehWhenCoinIsDime() {
        var dime = new Coin(DIME.diameter.doubleValue(), DIME.weight.doubleValue(), DIME.value.doubleValue());
        var actual = CoinService.isValidCoin(dime);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinIsNickel() {
        var nickel = new Coin(NICKEL.diameter.doubleValue(), NICKEL.weight.doubleValue(), NICKEL.value.doubleValue());
        var actual = CoinService.isValidCoin(nickel);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinWeightIsWithinMarginOfError() {
        var nickel = new Coin(NICKEL.diameter.doubleValue(), 21.206, NICKEL.value.doubleValue());
        var actual = CoinService.isValidCoin(nickel);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinDiameterIsWithinMarginOfError() {
        var nickel = new Coin(4.995, NICKEL.weight.doubleValue(), NICKEL.value.doubleValue());
        var actual = CoinService.isValidCoin(nickel);

        assertTrue(actual);
    }

    @Test
    public void countChange_ShouldReturnValueForSingleCoin() {
        var coins = Collections.singletonList(NICKEL);
        var actual = coinService.countChange(coins);

        assertEquals(NICKEL.value, actual);
    }

    @Test
    public void countChange_ShouldReturnValueForMultipleCoins() {
        var coins = Arrays.asList(QUARTER, DIME);
        var actual = coinService.countChange(coins);

        var expected = QUARTER.value.add(DIME.value);
        assertEquals(expected, actual);
    }

    @Test
    public void countChange_ShouldReturnZeroWhenListEmpty() {
        List<Coin> coins = Collections.emptyList();
        var actual = coinService.countChange(coins);

        assertEquals(BigDecimal.ZERO, actual);
    }

    @Test
    public void countChange_ShouldReturnZeroWhenListNull() {
        var actual = coinService.countChange(null);

        assertEquals(BigDecimal.ZERO, actual);
    }

    @Test
    public void returnCorrectChange_ShouldReturnASingleCoin() {
        var productCost = new BigDecimal(1.00);
        var funds = new BigDecimal(1.05);

        var actual = coinService.returnChange(productCost, funds);

        var expectedCoins = Collections.singletonList(NICKEL);
        assertEquals(expectedCoins, actual);
    }

    @Test
    public void returnCorrectChange_ShouldReturnAMultipleCoins() {
        var productCost = new BigDecimal(1.00);
        var funds = new BigDecimal(1.40);

        var actual = coinService.returnChange(productCost, funds);

        var expectedCoins = Arrays.asList(QUARTER, DIME, NICKEL);
        assertEquals(expectedCoins, actual);
    }

    @Test
    public void returnCorrectChange_ShouldReturnSingleExactCoin() {
        var actual = coinService.returnChange(BigDecimal.ZERO, QUARTER.value);

        var expectedCoins = Collections.singletonList(QUARTER);
        assertEquals(expectedCoins, actual);
    }

    @Test
    public void returnCorrectChange_ShouldReturnAMultipleCoinsOfDuplicateDenominations() {
        var productCost = new BigDecimal(1.00);
        var funds = new BigDecimal(1.75);

        var actual = coinService.returnChange(productCost, funds);

        var expectedCoins = Arrays.asList(QUARTER, QUARTER, QUARTER);
        assertEquals(expectedCoins, actual);
    }
}
