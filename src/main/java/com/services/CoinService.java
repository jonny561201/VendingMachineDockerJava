package com.services;

import com.models.Coin;

import static com.models.Coin.*;

public class CoinService {
    public boolean isValidCoin(Coin coinToValidate) {
        if (coinToValidate == Dollar) {
            return true;
        }
        if (coinToValidate == Quarter) {
            return true;
        }
        if (coinToValidate == Dime) {
            return true;
        }
        if (coinToValidate == Nickel) {
            return true;
        }
        return false;
    }
}
