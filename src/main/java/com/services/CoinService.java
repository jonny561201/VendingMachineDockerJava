package com.services;

import com.models.Coin;

import static com.models.Coin.Dollar;

public class CoinService {
    public boolean isValidCoin(Coin coinToValidate) {
        if (coinToValidate == Dollar) {
            return true;
        }
        return false;
    }
}
