package services;

import com.Database.ProductDatabase;
import com.models.Product;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductService service;
    private ProductDatabase mockDatabase;


    @Before
    public void Setup() {
        mockDatabase = mock(ProductDatabase.class);
        service = new ProductService(mockDatabase);
    }

    @Test
    public void isProductAvailable_ShouldReturnFalseWhenProductUnavailable() {
        List<Product> products = new ArrayList<>();
        String productLocation = "G12";
        when(mockDatabase.getProductsByLocation(productLocation)).thenReturn(products);

        boolean actual = service.isProductAvailable(productLocation);

        assertFalse(actual);
    }

    @Test
    public void isProductAvailable_ShouldReturnTrueWhenProductAvailable() {
        List<Product> products = Collections.singletonList(new Product());
        String productLocation = "G12";
        when(mockDatabase.getProductsByLocation(productLocation)).thenReturn(products);

        boolean actual = service.isProductAvailable(productLocation);

        assertTrue(actual);
    }

    @Test
    public void getProductCost_ShouldReturnProductCost() {
        String productLocation = "A0";
        BigDecimal expected = new BigDecimal("0.75");
        Product product = new Product();
        product.setCost(expected);
        List<Product> products = Collections.singletonList(product);
        when(mockDatabase.getProductsByLocation(productLocation)).thenReturn(products);

        BigDecimal actual = service.getProductCost(productLocation);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductCost_ShouldReturnProductCostForDifferentProduct() {
        String productLocation = "A1";
        BigDecimal expected = new BigDecimal("0.95");
        Product product = new Product();
        product.setCost(expected);
        List<Product> products = Collections.singletonList(product);
        when(mockDatabase.getProductsByLocation(productLocation)).thenReturn(products);

        BigDecimal actual = service.getProductCost(productLocation);

        assertEquals(expected, actual);
    }

    @Test
    public void hasSufficientFunds_ShouldReturnFalseWhenCostIsGreaterThanFunds() {
        BigDecimal productCost = new BigDecimal(1.00);
        BigDecimal funds = new BigDecimal(0.75);

        boolean actual = service.hasSufficientFunds(productCost, funds);

        assertFalse(actual);
    }

    @Test
    public void hasSufficientFunds_ShouldReturnTrueWhenFundsAreGreaterThanCost() {
        BigDecimal productCost = new BigDecimal(1.00);
        BigDecimal funds = new BigDecimal(1.75);

        boolean actual = service.hasSufficientFunds(productCost, funds);

        assertTrue(actual);
    }

    @Test
    public void hasSufficientFunds_ShouldReturnTrueWhenFundsAreEqualToCost() {
        BigDecimal productCost = new BigDecimal(1.00);
        BigDecimal funds = new BigDecimal(1.00);

        boolean actual = service.hasSufficientFunds(productCost, funds);

        assertTrue(actual);
    }
}
