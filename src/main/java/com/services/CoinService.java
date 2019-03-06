package com.services;

import com.models.Coin;

import static com.models.Coin.Dollar;
import static com.models.Coin.Quarter;

public class CoinService {
    public boolean isValidCoin(Coin coinToValidate) {
        if (coinToValidate == Dollar) {
            return true;
        }
        if (coinToValidate == Quarter) {
            return true;
        }
        return false;
    }
}
