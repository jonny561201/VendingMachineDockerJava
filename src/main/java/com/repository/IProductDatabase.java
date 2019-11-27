package com.repository;

import com.models.Product;

import java.util.List;

public interface IProductDatabase {
    List<Product> getProductsByLocation(String productLocation);
    List<Product> getProductByType(String productType);
}
