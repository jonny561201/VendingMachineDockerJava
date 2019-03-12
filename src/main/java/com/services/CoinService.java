package com.services;

import com.models.Coin;

import java.math.BigDecimal;

import static com.models.Coin.ValidCoins;

public class CoinService {


    public boolean isValidCoin(Coin coinToValidate) {
        return ValidCoins.stream().anyMatch(x -> hasValidDimensions(x, coinToValidate));
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
