package acceptance.steps;

import com.Application;
import com.repository.ProductDatabase;
import com.models.Coin;
import com.models.Product;
import com.models.RequestProduct;
import com.models.VendProduct;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.models.Coin.DOLLAR;
import static com.models.Coin.QUARTER;
import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendingMachineSteps {

    @LocalServerPort
    private int port;

    @Autowired
    private ProductDatabase database;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    private ResponseEntity<String> healthCheck;
    private ResponseEntity<VendProduct> purchase;
    private String expectedProduct;
    private String expectedLocation;


    @After
    public void tearDown() {
        database.deleteProductsByLocation(expectedLocation);
    }

    @When("^I query the healthCheck endpoint$")
    public void whenIQueryTheHealthCheckEndpoint() {
        healthCheck = getHealthCheck();
    }

    @Then("^I get a success status code$")
    public void iGetASuccessStatusCode() {
        assertEquals(HttpStatus.OK, healthCheck.getStatusCode());
    }

    @Given("^The database is stocked with (.*) at (.*) for \\$(\\d+.\\d+)$")
    public void theDatabaseIsStockedWithItems(String productName, String location, double cost) {
        expectedProduct = productName;
        expectedLocation = location;
        BigDecimal expectedCost = new BigDecimal(cost);
        Product product = new Product();
        product.setCost(expectedCost);
        product.setLocation(location);
        product.setName(expectedProduct);

        stockProducts(Collections.singletonList(product));
    }

    @When("^I purchase a product at location (.*)$")
    public void iQueryForProductLocationA(String productLocation) {
        List<Coin> coins = Arrays.asList(DOLLAR, QUARTER);
        purchase = postPurchaseProduct(productLocation, coins);
    }

    @Then("^I should successfully purchase product$")
    public void iShouldReturnOnlyMatchingItems() {
        assertEquals(expectedProduct, purchase.getBody().getProduct().getName());
        assertEquals("Thank You!", purchase.getBody().getMessage());
    }

    private ResponseEntity<String> getHealthCheck() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        return restTemplate.exchange(
                createURLWithPort("/healthcheck"),
                HttpMethod.GET, entity, String.class);
    }


    private ResponseEntity<VendProduct> postPurchaseProduct(String productLocation, List<Coin> coins) {
        RequestProduct requestProduct = new RequestProduct();
        requestProduct.setProductLocation(productLocation);
        requestProduct.setInsertedCoins(coins);

        return restTemplate.postForEntity(
                createURLWithPort("/purchaseProduct"), requestProduct, VendProduct.class);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private void stockProducts(List<Product> products) {
        database.batchInsertProducts(products);
    }
}
