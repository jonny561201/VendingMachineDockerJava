package com.services;

import com.models.Coin;
import com.utilities.RoundValues;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.models.Coin.VALID_COINS;

public class CoinService {


    public static boolean isValidCoin(Coin coinToValidate) {
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
            BigDecimal roundedCoinValue = RoundValues.round(coin.value);
            while(roundedCoinValue.compareTo(RoundValues.round(change)) <= 0) {
                change = RoundValues.round(change).subtract(roundedCoinValue);
                changeToReturn.add(coin);
            }
        }
        return changeToReturn;
    }

    private static boolean hasValidDimensions(Coin validCoin, Coin coinToValidate) {
        return hasValidDimension(validCoin.diameter, coinToValidate.diameter)
                && hasValidDimension(validCoin.weight, coinToValidate.weight);
    }

    private static boolean hasValidDimension(BigDecimal validDimension, BigDecimal dimensionToCompare) {
        return RoundValues.round(validDimension)
                .equals(RoundValues.round(dimensionToCompare));
    }
}
