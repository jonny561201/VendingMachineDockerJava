package com.Database;

import com.models.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDatabase {

    public List<Product> getProductsByLocation(String productLocation) {
        return null;
    }

    public List<Product> getProductByType(String productType) {
        return null;
    }
}
