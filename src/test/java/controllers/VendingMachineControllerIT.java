package controllers;

import com.Database.ProductDatabase;
import com.controllers.VendingMachineController;
import com.services.CoinService;
import com.services.ProductService;
import org.junit.Before;

import static org.mockito.Mockito.mock;

public class VendingMachineControllerIT {

    private CoinService coinService;
    private ProductDatabase database;
    private ProductService productService;
    private VendingMachineController controller;

    @Before
    public void Setup() {
        coinService = new CoinService();
        database = mock(ProductDatabase.class);
        productService = new ProductService(database);
        controller = new VendingMachineController(coinService, productService);
    }


}
