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
        List<Coin> validCoins = coins.stream()
                .filter(CoinService::isValidCoin)
                .collect(Collectors.toList());
        BigDecimal funds = coinService.countChange(validCoins);

        if(!productService.isProductAvailable(productSelection)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Unavailable");
        }
        BigDecimal productCost = productService.getProductCost(productSelection);
        if (!productService.hasSufficientFunds(productCost, funds)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds");
        }
        List<Coin> change = coinService.returnChange(productCost, funds);

        return createResponse(change);
    }

    private VendProduct createResponse(List<Coin> change) {
        VendProduct vendProduct = new VendProduct();
        vendProduct.setChange(change);
        vendProduct.setProduct(productService.selectedProduct);
        vendProduct.setMessage("Thank You!");
        return vendProduct;
    }
}
