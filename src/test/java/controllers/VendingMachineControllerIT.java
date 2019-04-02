package controllers;

import com.Database.ProductDatabase;
import com.controllers.VendingMachineController;
import com.models.Coin;
import com.models.Product;
import com.models.VendProduct;
import com.services.CoinService;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.models.Coin.DOLLAR;
import static com.models.Coin.QUARTER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VendingMachineControllerIT {

    private CoinService coinService;
    private ProductDatabase database;
    private ProductService productService;
    private VendingMachineController controller;

    @Before
    public void Setup() {
        coinService = new CoinService();
        database = mock(ProductDatabase.class);
        productService = new ProductService(database);
        controller = new VendingMachineController(coinService, productService);
    }

    @Test
    public void purchase_ShoulReturnErrorMessageWhenInsufficientFundsSupplied() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER);
        String productLocation = "G3";
        Product product = new Product();
        product.setCost(new BigDecimal(1.30));
        when(database.getProductsByLocation(productLocation)).thenReturn(Collections.singletonList(product));

        VendProduct actual = controller.purchase(productLocation, coins);

        assertEquals("Insufficient Funds", actual.getMessage());
    }
}
