package com.services;

import com.models.Coin;
import com.utilities.RoundValues;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static com.models.Coin.VALID_COINS;

@Component
public class CoinService {


    public static boolean isValidCoin(Coin coinToValidate) {
        return VALID_COINS.stream().anyMatch(x -> hasValidDimensions(x, coinToValidate));
    }

    public BigDecimal countChange(List<Coin> coins) {
        return Optional.ofNullable(coins).stream().flatMap(Collection::stream)
                .map(x -> x.value)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public List<Coin> returnChange(BigDecimal productCost, BigDecimal funds) {
        var change = funds.subtract(productCost);
        List<Coin> changeToReturn = new ArrayList<>();

        for (Coin coin : VALID_COINS) {
            var roundedCoinValue = RoundValues.round(coin.value);
            while (roundedCoinValue.compareTo(RoundValues.round(change)) <= 0) {
                change = RoundValues.round(change).subtract(roundedCoinValue);
                changeToReturn.add(coin);
            }
        }
        return changeToReturn;
    }

    private static boolean hasValidDimensions(Coin validCoin, Coin coinToValidate) {
        return RoundValues.round(validCoin.diameter).equals(RoundValues.round(coinToValidate.diameter))
        && RoundValues.round(validCoin.weight).equals(RoundValues.round(coinToValidate.weight));
    }
}
