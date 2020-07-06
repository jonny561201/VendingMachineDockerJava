package controllers;

import com.controllers.VendingRestController;
import com.controllers.VendingMachineController;
import com.models.Coin;
import com.models.RequestProduct;
import com.models.VendProduct;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static com.models.Coin.DOLLAR;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class VendingRestControllerTest {

    private VendingMachineController vendingMachineController;
    private VendingRestController controller;
    private RequestProduct request;
    private static final String PRODUCT_LOCATION = "A1";
    private static final List<Coin> INSERTED_COINS = Collections.singletonList(DOLLAR);

    @Before
    public void Setup() {
        request = new RequestProduct();
        vendingMachineController = mock(VendingMachineController.class);
        controller = new VendingRestController(vendingMachineController);
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
        VendProduct expected = new VendProduct(null, null, null);
        when(vendingMachineController.purchase(PRODUCT_LOCATION, INSERTED_COINS)).thenReturn(expected);

        VendProduct actual = controller.purchaseProduct(request);

        assertEquals(expected, actual);
    }
}
