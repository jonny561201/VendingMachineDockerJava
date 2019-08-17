package com.Database;

import com.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDatabase implements IProductDatabase{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getProductsByLocation(String productLocation) {
        String selectQuery = "SELECT * FROM products WHERE location = ?";

        return jdbcTemplate.query(selectQuery, new Object[]{productLocation}, new BeanPropertyRowMapper<>(Product.class));
    }

    public List<Product> getProductByType(String productType) {
        return null;
    }
}
