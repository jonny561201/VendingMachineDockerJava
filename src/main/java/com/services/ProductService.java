package com.services;

import com.repository.ProductDatabase;
import com.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductService {

    private ProductDatabase database;
    public Product selectedProduct;

    @Autowired
    public ProductService(ProductDatabase database) {
        this.database = database;
    }

    public boolean isProductAvailable(String productLocation) {
        var productsByLocation = database.getProductsByLocation(productLocation);
        selectedProduct = productsByLocation.stream().findFirst().orElse(null);
        return productsByLocation.stream().findAny().isPresent();
    }

    public BigDecimal getProductCost(String productLocation) {
        return selectedProduct != null
                ? selectedProduct.getCost()
                : database.getProductsByLocation(productLocation).stream()
                .findFirst().orElse(new Product())
                .getCost();
    }

    public boolean hasSufficientFunds(BigDecimal productCost, BigDecimal funds) {
        return funds.compareTo(productCost) >= 0;
    }
}
