package com.controllers;

import com.models.Coin;
import com.models.VendProduct;
import com.services.CoinService;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VendingMachineController {
    private CoinService coinService;
    private ProductService productService;

    @Autowired
    public VendingMachineController(CoinService coinService, ProductService productService) {
        this.coinService = coinService;
        this.productService = productService;
    }

    public VendProduct purchase(String productSelection, List<Coin> coins) {
        var validCoins = coins.stream()
                .filter(CoinService::isValidCoin)
                .collect(Collectors.toList());
        var funds = coinService.countChange(validCoins);
        var productCost = productService.getProductCost(productSelection);
        validatePurchase(productSelection, funds, productCost);
        var change = coinService.returnChange(productCost, funds);

        return new VendProduct("Thank You!", change, productService.selectedProduct);
    }

    private void validatePurchase(String productSelection, BigDecimal funds, BigDecimal productCost) {
        if (!productService.isProductAvailable(productSelection)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Unavailable");
        }
        if (!productService.hasSufficientFunds(productCost, funds)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds");
        }
    }

}
