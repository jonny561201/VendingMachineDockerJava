package acceptance.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductDatabaseSteps {
    @Given("^The database is stocked with items$")
    public void theDatabaseIsStockedWithItems() {
        System.out.println("Made it Here!!!!!!!!!!!!!!!");
    }

    @When("^I query for product location (.*)$")
    public void iQueryForProductLocationA(String productLocation) {
    }

    @Then("^I should return only matching items$")
    public void iShouldReturnOnlyMatchingItems() {
    }
}
