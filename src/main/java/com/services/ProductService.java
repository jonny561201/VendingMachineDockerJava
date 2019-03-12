package com.services;

import com.Database.ProductDatabase;

public class ProductService {
    private ProductDatabase database;

    public ProductService(ProductDatabase database) {

        this.database = database;
    }

    public boolean isProductAvailable(String productLocation) {
        return database.getProductsByLocation(productLocation).stream().findAny().isPresent();
    }
}
