package services;

import com.Database.ProductDatabase;
import com.models.Product;
import com.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
}
