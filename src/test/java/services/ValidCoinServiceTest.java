package services;

import com.models.Coin;
import com.services.CoinService;
import org.junit.Before;
import org.junit.Test;

import static com.models.Coin.*;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class ValidCoinServiceTest {

    private CoinService coinService;

    @Before
    public void Setup() {
        coinService = new CoinService();
    }

    @Test
    public void isValidCoin_ShouldReturnFalseWhenCoinHasInvalidHeight() {
        Coin invalidWeight = new Coin(Dollar.diameter, 99,99);

        boolean actual = coinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnFalseWhenCoinHasInvalidWeight() {
        Coin invalidWeight = new Coin(99.0, Dollar.weight, 99.0);

        boolean actual = coinService.isValidCoin(invalidWeight);

        assertFalse(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinIsDollar() {
        Coin dollar = new Coin(Dollar.diameter, Dollar.weight, Dollar.value);
        boolean actual = coinService.isValidCoin(dollar);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTruehWhenCoinIsQuarter() {
        Coin quarter = new Coin(Quarter.diameter, Quarter.weight, Quarter.value);
        boolean actual = coinService.isValidCoin(quarter);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTruehWhenCoinIsDime() {
        Coin dime = new Coin(Dime.diameter, Dime.weight, Dime.value);
        boolean actual = coinService.isValidCoin(dime);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTrueWhenCoinIsNickel() {
        Coin nickel = new Coin(Nickel.diameter, Nickel.weight, Nickel.value);
        boolean actual = coinService.isValidCoin(nickel);

        assertTrue(actual);
    }
}
