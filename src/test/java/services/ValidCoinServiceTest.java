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
    public void isValidCoin_ShouldReturnTrueWhenCoinIsDollar() {
        boolean actual = coinService.isValidCoin(Dollar);

        assertTrue(actual);
    }

    @Test
    public void isValidCoin_ShouldReturnTruehWhenCoinIsQuarter() {
        boolean actual = coinService.isValidCoin(Quarter);

        assertTrue(actual);
    }
}
