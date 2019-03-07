package com.models;

import java.util.Arrays;
import java.util.List;

public class Coin {
    public static Coin Dollar = new Coin(8.1, 26.5, 1.00);
    public static Coin Quarter = new Coin(5.67, 24.26, 0.25);
    public static Coin Dime = new Coin(2.268, 17.91, 0.10);
    public static Coin Nickel = new Coin(5, 21.21, 0.05);
    public static List<Coin> ValidCoins = Arrays.asList(Dollar, Quarter, Dime, Nickel);

    public double diameter;
    public double weight;
    public double value;

    public Coin(double diameter, double weight, double value) {
        this.diameter = diameter;
        this.weight = weight;
        this.value = value;
    }
}
