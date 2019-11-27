package com.models;

import java.util.List;

public class VendProduct {

    private String message;
    private List<Coin> change;
    private Product product;

    public VendProduct(String message, List<Coin> change, Product product) {
        this.message = message;
        this.change = change;
        this.product = product;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Coin> getChange() {
        return change;
    }

    public void setChange(List<Coin> change) {
        this.change = change;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
