package com.controllers;

import com.models.Coin;
import com.models.VendProduct;
import com.services.CoinService;

import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineController {
    private CoinService coinService;

    public VendingMachineController(CoinService coinService) {
        this.coinService = coinService;
    }

    public VendProduct purchase(String productSelection, List<Coin> coins) {
        List<Coin> validCoins = coins.stream()
                .filter(CoinService::isValidCoin)
                .collect(Collectors.toList());
        coinService.countChange(validCoins);

        return null;
    }
}
