package controllers;

import com.controllers.VendingMachineController;
import com.services.CoinService;
import org.junit.Before;

public class VendingMachineControllerIT {

    private CoinService coinService;
    private VendingMachineController controller;

    @Before
    public void Setup() {
        coinService = new CoinService();
        controller = new VendingMachineController(coinService);
    }

}
