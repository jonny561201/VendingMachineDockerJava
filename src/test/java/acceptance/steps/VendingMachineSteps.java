package acceptance.steps;

import com.Application;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendingMachineSteps {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    private ResponseEntity<String> healthCheck;

    @When("^I query the healthCheck endpoint$")
    public void whenIQueryTheHealthCheckEndpoint() {
        healthCheck = getHealthCheck();
    }

    @Then("^I get a success status code$")
    public void iGetASuccessStatusCode() {
        assertEquals(HttpStatus.OK, healthCheck.getStatusCode());
    }

    private ResponseEntity<String> getHealthCheck() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        return restTemplate.exchange(
                createURLWithPort("/healthcheck"),
                HttpMethod.GET, entity, String.class);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
