package controllers;

import com.Application;
import com.models.RequestProduct;
import com.models.VendProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static com.models.Coin.DOLLAR;
import static com.models.Coin.QUARTER;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestControllerIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void getProduct_ShouldReturnHelloWorld() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/healthcheck"),
                HttpMethod.GET, entity, String.class);

        HttpStatus status = response.getStatusCode();

        assertEquals(HttpStatus.OK, status);
    }

    @Test
    public void purchaseProduct_ShouldSuccessfullyPurchaseProduct() {
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setProductLocation("B2");
        requestProduct.setInsertedCoins(Arrays.asList(DOLLAR, QUARTER, QUARTER));

        ResponseEntity<VendProduct> response = restTemplate.postForEntity(
                createURLWithPort("/purchaseProduct"), requestProduct, VendProduct.class);

//        VendProduct response = restTemplate.postForObject(
//                createURLWithPort("/purchaseProduct"), requestProduct, VendProduct.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Pepsi", response.getBody().getProduct().getName());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}

