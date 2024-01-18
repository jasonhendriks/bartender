package ca.hendriks.bartender.inventory.functionaltest;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InventorySteps {

    @When("user tries to add Vodka to their inventory")
    public void user_tries_to_add_vodka_to_their_inventory() {
    }

    @Then("the user's inventory should contain:")
    public void the_user_s_inventory_should_contain(final DataTable dataTable) {
    }

}
