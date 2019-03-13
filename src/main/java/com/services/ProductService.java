package com.services;

import com.Database.ProductDatabase;

import java.math.BigDecimal;

public class ProductService {
    private ProductDatabase database;

    public ProductService(ProductDatabase database) {

        this.database = database;
    }

    public boolean isProductAvailable(String productLocation) {
        return database.getProductsByLocation(productLocation).stream().findAny().isPresent();
    }

    public BigDecimal getProductCost(String productLocation) {
        return database.getProductsByLocation(productLocation).stream().findFirst().get().getCost();
    }
}
