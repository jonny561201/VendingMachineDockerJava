package com.controllers;

import com.models.Coin;
import com.models.VendProduct;
import com.services.CoinService;
import com.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineController {
    private CoinService coinService;
    private ProductService productService;

    public VendingMachineController(CoinService coinService, ProductService productService) {
        this.coinService = coinService;
        this.productService = productService;
    }

    public VendProduct purchase(String productSelection, List<Coin> coins) {
        VendProduct vendProduct = new VendProduct();
        List<Coin> validCoins = coins.stream()
                .filter(CoinService::isValidCoin)
                .collect(Collectors.toList());
        BigDecimal funds = coinService.countChange(validCoins);

        if(!productService.isProductAvailable(productSelection)) {
            vendProduct.setMessage("Product Unavailable");
            return vendProduct;
        }
        BigDecimal productCost = productService.getProductCost(productSelection);
        if (!productService.hasSufficientFunds(productCost, funds)) {
            vendProduct.setMessage("Insufficient Funds");
            return vendProduct;
        }
        coinService.returnChange(productCost, funds);

        return null;
    }
}
