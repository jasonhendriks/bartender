package ca.hendriks.bartender.inventory.functionaltest;

import ca.hendriks.bartender.common.Ingredient;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientSteps {

    private final DomainSpecificLanguage dsl;

    public IngredientSteps(final DomainSpecificLanguage dsl) {
        this.dsl = dsl;
    }

    @Given("Vodka is an ingredient")
    public void vodka_is_an_ingredient() {
    }

    @When("the administrator adds an ingredient:")
    public void the_administrator_adds_an_ingredient(final DataTable dataTable) {
        dsl.addIngredient();
    }

    @Then("the ingredients should be:")
    public void the_ingredients_should_be(final DataTable dataTable) {
        final List<Ingredient> ingredients = dsl.findIngredients();
        final List<Map<String, String>> expectedItems = dataTable.asMaps();
        assertEquals(ingredients.size(), expectedItems.size());
        for (int c = 0; c < ingredients.size(); c++) {
            final Map<String, String> expected = expectedItems.get(c);
            final Ingredient actual = ingredients.get(c);
            final DataTableChecker<Ingredient> checker = new DataTableChecker<>(expected, actual);
            assertAll(
                    () -> checker.assertEquals(e -> e, Ingredient::type, "Type"),
                    () -> checker.assertEquals(e -> e, Ingredient::name, "Name"),
                    () -> checker.validateMapKeys()
            );
        }
    }

}
