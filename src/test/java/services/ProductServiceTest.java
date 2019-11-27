package services;

import com.repository.ProductDatabase;
import com.models.Product;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
        var productLocation = "G12";
        when(mockDatabase.getProductsByLocation(productLocation)).thenReturn(products);

        var actual = service.isProductAvailable(productLocation);

        assertFalse(actual);
    }

    @Test
    public void isProductAvailable_ShouldReturnTrueWhenProductAvailable() {
        var products = Collections.singletonList(new Product());
        var productLocation = "G12";
        when(mockDatabase.getProductsByLocation(productLocation)).thenReturn(products);

        var actual = service.isProductAvailable(productLocation);

        assertTrue(actual);
    }

    @Test
    public void getProductCost_ShouldReturnProductCost() {
        var productLocation = "A0";
        var expected = new BigDecimal("0.75");
        var product = new Product();
        product.setCost(expected);
        var products = Collections.singletonList(product);
        when(mockDatabase.getProductsByLocation(productLocation)).thenReturn(products);

        var actual = service.getProductCost(productLocation);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductCost_ShouldReturnProductCostForDifferentProduct() {
        var productLocation = "A1";
        var expected = new BigDecimal("0.95");
        var product = new Product();
        product.setCost(expected);
        var products = Collections.singletonList(product);
        when(mockDatabase.getProductsByLocation(productLocation)).thenReturn(products);

        var actual = service.getProductCost(productLocation);

        assertEquals(expected, actual);
    }

    @Test
    public void getProductCost_ShouldReturnDefaultCostOfZeroWhenNoProduct() {
        var productLocation = "A1";
        when(mockDatabase.getProductsByLocation(productLocation)).thenReturn(Collections.EMPTY_LIST);
        var actual = service.getProductCost(productLocation);

        assertEquals(BigDecimal.ZERO, actual);
    }

    @Test
    public void getProductCost_ShouldNotCallDatabaseIfCostCached() {
        var productLocation = "A4";
        var cost = new BigDecimal(0.50);
        var product = new Product();
        product.setCost(cost);
        service.selectedProduct = product;

        var actual = service.getProductCost(productLocation);

        verify(mockDatabase, times(0)).getProductsByLocation(productLocation);
        assertEquals(cost, actual);
    }

    @Test
    public void hasSufficientFunds_ShouldReturnFalseWhenCostIsGreaterThanFunds() {
        var productCost = new BigDecimal(1.00);
        var funds = new BigDecimal(0.75);

        boolean actual = service.hasSufficientFunds(productCost, funds);

        assertFalse(actual);
    }

    @Test
    public void hasSufficientFunds_ShouldReturnTrueWhenFundsAreGreaterThanCost() {
        var productCost = new BigDecimal(1.00);
        var funds = new BigDecimal(1.75);

        var actual = service.hasSufficientFunds(productCost, funds);

        assertTrue(actual);
    }

    @Test
    public void hasSufficientFunds_ShouldReturnTrueWhenFundsAreEqualToCost() {
        var productCost = new BigDecimal(1.00);
        var funds = new BigDecimal(1.00);

        var actual = service.hasSufficientFunds(productCost, funds);

        assertTrue(actual);
    }
}
