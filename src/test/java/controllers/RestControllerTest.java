package controllers;

import com.controllers.RestController;
import com.controllers.VendingMachineController;
import com.models.Coin;
import com.models.RequestProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.models.Coin.DOLLAR;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RestControllerTest {

    private VendingMachineController vendingMachineController;
    private RestController controller;

    @Before
    public void Setup() {
        vendingMachineController = mock(VendingMachineController.class);
        controller = new RestController(vendingMachineController);
    }

    @Test
    public void purchaseProduct_ShouldCallVendingMachineControllerWithProduct() {
        RequestProduct request = new RequestProduct();
        List<Coin> insertedCoins = Collections.singletonList(DOLLAR);
        String productLocation = "A1";
        request.setInsertedCoins(insertedCoins);
        request.setProductLocation(productLocation);

        controller.purchaseProduct(request);

        verify(vendingMachineController, Mockito.times(1)).purchase(productLocation, insertedCoins);
    }
}
