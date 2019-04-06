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

import static com.models.Coin.*;
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
    public void purchase_ShouldReturnErrorMessageWhenInsufficientFundsSupplied() {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER);
        String productLocation = "G3";
        Product product = new Product();
        product.setCost(new BigDecimal(1.30));
        when(database.getProductsByLocation(productLocation)).thenReturn(Collections.singletonList(product));

        VendProduct actual = controller.purchase(productLocation, coins);

        assertEquals("Insufficient Funds", actual.getMessage());
    }

    @Test
    public void purchase_ShouldReturnErrorMessageWhenProductUnavailable() {
        List<Coin> coins = Arrays.asList(QUARTER, DIME);
        String productLocation = "B2";
        when(database.getProductsByLocation(productLocation)).thenReturn(Collections.emptyList());

        VendProduct actual = controller.purchase(productLocation, coins);

        assertEquals("Product Unavailable", actual.getMessage());
    }

    @Test
    public void purchase_ShouldReturnChangeWhenProductIsAbleToBePurchased() {
        String productLocation = "D5";
        List<Coin> coins = Arrays.asList(DIME, QUARTER);
        Product product = new Product();
        product.setCost(QUARTER.value);
        List<Product> products = Collections.singletonList(product);
        when(database.getProductsByLocation(productLocation)).thenReturn(products);

        VendProduct actual = controller.purchase(productLocation, coins);

        List<Coin> expected = Collections.singletonList(DIME);
        assertEquals(expected, actual.getChange());
    }
}
