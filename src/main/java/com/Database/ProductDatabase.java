package com.Database;

import com.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDatabase implements IProductDatabase {

    private static final String BATCH_INSERT = "INSERT into products (name, location, cost) values ";
    private static final String SELECT_PRODUCT = "SELECT * FROM products WHERE location = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM products WHERE location = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getProductsByLocation(String productLocation) {
        return jdbcTemplate.query(SELECT_PRODUCT, new Object[]{productLocation}, new BeanPropertyRowMapper<>(Product.class));
    }

    public void batchInsertProducts(List<Product> products) {
        StringBuilder build = new StringBuilder(BATCH_INSERT);
        products.forEach(x -> build.append(buildInsertProductString(x)));

        int lastChar = build.length() - 1;
        build.replace(lastChar, lastChar + 1, ";");

        jdbcTemplate.update(build.toString());
    }

    public void deleteProductsByLocation(String productLocation) {
        jdbcTemplate.update(DELETE_PRODUCT, productLocation);
    }

    public List<Product> getProductByType(String productType) {
        return null;
    }

    private String buildInsertProductString(Product product) {
        return String.format("('%s', '%s', %s),", product.getName(), product.getLocation(), product.getCost());
    }
}
