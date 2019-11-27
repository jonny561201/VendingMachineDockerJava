package controllers;

import com.controllers.VendingMachineController;
import com.models.Coin;
import com.services.CoinService;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static com.models.Coin.*;
import static org.mockito.Mockito.*;

public class VendingMachineControllerTest {

    private CoinService coinService;
    private ProductService productService;
    private VendingMachineController controller;
    public static final String PRODUCT_SELECTION = "B4";

    @Before
    public void Setup() {
        coinService = mock(CoinService.class);
        productService = mock(ProductService.class);
        when(productService.isProductAvailable(PRODUCT_SELECTION)).thenReturn(true);
        when(productService.hasSufficientFunds(any(), any())).thenReturn(true);

        controller = new VendingMachineController(coinService, productService);
    }

    @Test
    public void purchase_ShouldCallCountChange() {
        var coins = Collections.singletonList(QUARTER);

        controller.purchase(PRODUCT_SELECTION, coins);

        verify(coinService, times(1)).countChange(coins);
    }

    @Test
    public void purchase_ShouldRejectInvalidCoins() {
        var invalidCoin = new Coin(99.0, 99.0, 5.0);
        var coins = Arrays.asList(DOLLAR, invalidCoin);

        controller.purchase(PRODUCT_SELECTION, coins);

        verify(coinService, times(1)).countChange(Collections.singletonList(DOLLAR));
    }

    @Test
    public void purchase_ShouldCallServiceToValidateProductCost() {
        var coins = Collections.singletonList(DIME);

        controller.purchase(PRODUCT_SELECTION, coins);

        verify(productService, times(1)).getProductCost(PRODUCT_SELECTION);
    }

    @Test
    public void purchase_ShouldCallServiceToValidateProductAvailability() {
        var coins = Collections.singletonList(DIME);

        controller.purchase(PRODUCT_SELECTION, coins);

        verify(productService, times(1)).isProductAvailable(PRODUCT_SELECTION);
    }

    @Test
    public void purchase_ShouldCallServiceSufficientFunds() {
        var coins = Arrays.asList(DIME, DOLLAR);
        var funds = new BigDecimal(1.15);
        when(coinService.countChange(coins)).thenReturn(funds);
        var productCost = new BigDecimal(1.10);
        when(productService.getProductCost(PRODUCT_SELECTION)).thenReturn(productCost);

        controller.purchase(PRODUCT_SELECTION, coins);

        verify(productService, times(1)).hasSufficientFunds(productCost, funds);
    }

    @Test
    public void purchase_ShouldCallReturnChange() {
        var coins = Arrays.asList(DIME, DOLLAR);

        controller.purchase(PRODUCT_SELECTION, coins);

        verify(coinService, times(1)).returnChange(Mockito.any(), Mockito.any());
    }
}
