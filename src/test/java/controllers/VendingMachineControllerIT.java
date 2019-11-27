package controllers;

import com.repository.ProductDatabase;
import com.controllers.VendingMachineController;
import com.models.Coin;
import com.models.Product;
import com.models.VendProduct;
import com.services.CoinService;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.web.server.ResponseStatusException;

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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void Setup() {
        coinService = new CoinService();
        database = mock(ProductDatabase.class);
        productService = new ProductService(database);
        controller = new VendingMachineController(coinService, productService);
    }

    @Test
    public void purchase_ShouldReturnErrorMessageWhenInsufficientFundsSupplied() {
        expectedException.expect(ResponseStatusException.class);
        expectedException.expectMessage("Insufficient Funds");

        var coins = Arrays.asList(DOLLAR, QUARTER);
        var productLocation = "G3";
        var product = new Product();
        product.setCost(new BigDecimal(1.30));
        when(database.getProductsByLocation(productLocation)).thenReturn(Collections.singletonList(product));

        controller.purchase(productLocation, coins);
    }

    @Test()
    public void purchase_ShouldReturnErrorMessageWhenProductUnavailable() {
        expectedException.expect(ResponseStatusException.class);
        expectedException.expectMessage("Product Unavailable");

        var coins = Arrays.asList(QUARTER, DIME);
        var productLocation = "B2";
        when(database.getProductsByLocation(productLocation)).thenReturn(Collections.emptyList());

        controller.purchase(productLocation, coins);
    }

    @Test
    public void purchase_ShouldReturnChangeWhenProductIsAbleToBePurchased() {
        var productLocation = "D5";
        var coins = Arrays.asList(DIME, QUARTER);
        var product = new Product();
        product.setCost(QUARTER.value);
        var products = Collections.singletonList(product);
        when(database.getProductsByLocation(productLocation)).thenReturn(products);

        var actual = controller.purchase(productLocation, coins);

        var expected = Collections.singletonList(DIME);
        assertEquals(expected, actual.getChange());
    }

    @Test
    public void purchase_ShouldReturnSuccessMessageWhenProductIsAbleToBePurchased() {
        var productLocation = "D5";
        var coins = Arrays.asList(DIME, QUARTER);
        var product = new Product();
        product.setCost(QUARTER.value);
        var products = Collections.singletonList(product);
        when(database.getProductsByLocation(productLocation)).thenReturn(products);

        var actual = controller.purchase(productLocation, coins);

        assertEquals("Thank You!", actual.getMessage());
    }

    @Test
    public void purchase_ShouldReturnProductWhenProductIsAbleToBePurchased() {
        var productLocation = "D5";
        var coins = Arrays.asList(DIME, QUARTER);
        var product = new Product();
        product.setCost(QUARTER.value);
        var products = Collections.singletonList(product);
        when(database.getProductsByLocation(productLocation)).thenReturn(products);

        var actual = controller.purchase(productLocation, coins);

        assertEquals(product, actual.getProduct());
    }
}
