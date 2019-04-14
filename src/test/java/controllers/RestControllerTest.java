package controllers;

import com.controllers.RestController;
import com.controllers.VendingMachineController;
import com.models.Coin;
import com.models.RequestProduct;
import com.models.VendProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static com.models.Coin.DOLLAR;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RestControllerTest {

    private VendingMachineController vendingMachineController;
    private RestController controller;
    private RequestProduct request;
    public static final String PRODUCT_LOCATION = "A1";
    public static final List<Coin> INSERTED_COINS = Collections.singletonList(DOLLAR);

    @Before
    public void Setup() {
        request = new RequestProduct();
        vendingMachineController = mock(VendingMachineController.class);
        controller = new RestController(vendingMachineController);
    }

    @Test
    public void purchaseProduct_ShouldCallVendingMachineControllerWithProduct() {
        request.setInsertedCoins(INSERTED_COINS);
        request.setProductLocation(PRODUCT_LOCATION);

        controller.purchaseProduct(request);

        verify(vendingMachineController, Mockito.times(1)).purchase(PRODUCT_LOCATION, INSERTED_COINS);
    }

    @Test
    public void purchaseProduct_ShouldReturnVendProduct() {
        request.setInsertedCoins(INSERTED_COINS);
        request.setProductLocation(PRODUCT_LOCATION);
        VendProduct expected = new VendProduct();
        when(vendingMachineController.purchase(PRODUCT_LOCATION, INSERTED_COINS)).thenReturn(expected);

        VendProduct actual = controller.purchaseProduct(request);

        assertEquals(expected, actual);
    }
}
