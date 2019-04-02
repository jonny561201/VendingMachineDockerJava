package controllers;

import com.controllers.VendingMachineController;
import com.models.Coin;
import com.models.VendProduct;
import com.services.CoinService;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.models.Coin.*;
import static org.mockito.Mockito.*;

public class VendingMachineControllerTest {

    private CoinService coinService;
    private ProductService productService;
    private VendingMachineController controller;

    @Before
    public void Setup() {
        coinService = mock(CoinService.class);
        productService = mock(ProductService.class);
        controller = new VendingMachineController(coinService, productService);
    }

    @Test
    public void purchase_ShouldCallCountChange() {
        String productSelection = "B4";
        List<Coin> coins = Collections.singletonList(QUARTER);

        controller.purchase(productSelection, coins);

        verify(coinService, times(1)).countChange(coins);
    }

    @Test
    public void purchase_ShouldRejectInvalidCoins() {
        Coin invalidCoin = new Coin(99.0, 99.0, 5.0);
        List<Coin> coins = Arrays.asList(DOLLAR, invalidCoin);
        String productLocation = "D1";

        controller.purchase(productLocation, coins);

        verify(coinService, times(1)).countChange(Collections.singletonList(DOLLAR));
    }

    @Test
    public void purchase_ShouldCallServiceToValidateProductCost() {
        List<Coin> coins = Collections.singletonList(DIME);
        String productLocation = "C4";

        controller.purchase(productLocation, coins);

        verify(productService, times(1)).getProductCost(productLocation);
    }
}
