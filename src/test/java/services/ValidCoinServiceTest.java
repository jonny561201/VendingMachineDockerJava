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

public class ValidCoinServiceTest {

    private CoinService coinService;

    @Before
    public void Setup() {
        coinService = new CoinService();
    }

    @Test
    public void isValidCoin_ShouldReturnFalseWhenCoinHasInvalidHeight() {
        Coin invalidWeight = new Coin(Dollar.diameter.doubleValue(), 99,99);

        boolean actual = coinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnFalseWhenCoinHasInvalidWeight() {
        Coin invalidWeight = new Coin(99.0, Dollar.weight.doubleValue(), 99.0);

        boolean actual = coinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinIsDollar() {
        Coin dollar = new Coin(Dollar.diameter.doubleValue(), Dollar.weight.doubleValue(), Dollar.value.doubleValue());
        boolean actual = coinService.isValidCoin(dollar);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTruehWhenCoinIsQuarter() {
        Coin quarter = new Coin(Quarter.diameter.doubleValue(), Quarter.weight.doubleValue(), Quarter.value.doubleValue());
        boolean actual = coinService.isValidCoin(quarter);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTruehWhenCoinIsDime() {
        Coin dime = new Coin(Dime.diameter.doubleValue(), Dime.weight.doubleValue(), Dime.value.doubleValue());
        boolean actual = coinService.isValidCoin(dime);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinIsNickel() {
        Coin nickel = new Coin(Nickel.diameter.doubleValue(), Nickel.weight.doubleValue(), Nickel.value.doubleValue());
        boolean actual = coinService.isValidCoin(nickel);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinWeightIsWithinMarginOfError() {
        Coin nickel = new Coin(Nickel.diameter.doubleValue(), 21.206, Nickel.value.doubleValue());
        boolean actual = coinService.isValidCoin(nickel);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinDiameterIsWithinMarginOfError() {
        Coin nickel = new Coin(4.995, Nickel.weight.doubleValue(), Nickel.value.doubleValue());
        boolean actual = coinService.isValidCoin(nickel);

        assertTrue(actual);
    }

    @Test
    public void countChange_ShouldReturnValueForSingleCoin() {
        List<Coin> coins = Collections.singletonList(Nickel);

        BigDecimal actual = coinService.countChange(coins);

        assertEquals(Nickel.value, actual);
    }
}
