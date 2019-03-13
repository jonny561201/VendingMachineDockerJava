package com.services;

import com.models.Coin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.models.Coin.DOLLAR;
import static com.models.Coin.VALID_COINS;

public class CoinService {


    public boolean isValidCoin(Coin coinToValidate) {
        return VALID_COINS.stream().anyMatch(x -> hasValidDimensions(x, coinToValidate));
    }

    public BigDecimal countChange(List<Coin> coins) {
        return coins.stream()
                .map(x -> x.value)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public List<Coin> returnChange(BigDecimal productCost, BigDecimal funds) {
        BigDecimal change = funds.subtract(productCost);
        List<Coin> changeToReturn = new ArrayList<>();

        for (Coin coin : VALID_COINS) {
            if (coin.value.compareTo(change) < 0) {
                change.subtract(coin.value);
                changeToReturn.add(coin);
            }
        }
        return changeToReturn;
    }

    private boolean hasValidDimensions(Coin validCoin, Coin coinToValidate) {
        return hasValidDimension(validCoin.diameter, coinToValidate.diameter)
                && hasValidDimension(validCoin.weight, coinToValidate.weight);
    }

    private boolean hasValidDimension(BigDecimal validDimension, BigDecimal dimensionToCompare) {
        return validDimension.setScale(2, BigDecimal.ROUND_HALF_UP)
                .equals(dimensionToCompare.setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
