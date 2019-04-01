package com.controllers;

import com.models.Coin;
import com.models.VendProduct;
import com.services.CoinService;

import java.util.List;

public class VendingMachineController {
    private CoinService coinService;

    public VendingMachineController(CoinService coinService) {
        this.coinService = coinService;
    }

    public VendProduct purchase(String productSelection, List<Coin> coins) {
        coinService.countChange(coins);

        return null;
    }
}
