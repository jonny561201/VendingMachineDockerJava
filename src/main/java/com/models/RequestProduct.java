package com.models;

import java.util.List;

public class RequestProduct {

    private String productLocation;
    private List<Coin> insertedCoins;

    public String getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(String productLocation) {
        this.productLocation = productLocation;
    }

    public List<Coin> getInsertedCoins() {
        return insertedCoins;
    }

    public void setInsertedCoins(List<Coin> insertedCoins) {
        this.insertedCoins = insertedCoins;
    }
}
