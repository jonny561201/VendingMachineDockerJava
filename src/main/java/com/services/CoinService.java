package com.services;

import com.models.Coin;

import static com.models.Coin.*;

public class CoinService {
    public boolean isValidCoin(Coin coinToValidate) {
        return ValidCoins.stream().anyMatch(x -> x.diameter == coinToValidate.diameter && x.weight == coinToValidate.weight);
    }
}
