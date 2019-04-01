package controllers;

import com.controllers.VendingMachineController;
import com.models.Coin;
import com.services.CoinService;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static com.models.Coin.DOLLAR;

public class VendingMachineControllerIT {

    private CoinService coinService;
    private VendingMachineController controller;

    @Before
    public void Setup() {
        coinService = new CoinService();
        controller = new VendingMachineController(coinService);
    }


}
