package ca.hendriks.bartender.web.functionaltest;

import ca.hendriks.bartender.web.inventory.Ingredient;
import ca.hendriks.bartender.web.inventory.IngredientType;
import io.cucumber.datatable.DataTable;
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

    @When("the administrator adds {string} into categrory {string},")
    public void the_administrator_adds_into_categrory(final String name, final String type) {
        final IngredientType typeEnum = IngredientType.valueOf(type);
        final Ingredient ingredient = new Ingredient(0, name, typeEnum);
        dsl.ingredients.addIngredient(ingredient);
    }

    @Then("the available ingredients should be:")
    public void the_ingredients_should_be(final DataTable dataTable) {
        final List<Ingredient> ingredients = dsl.ingredients.findIngredients();
        final List<Map<String, String>> expectedItems = dataTable.asMaps();
        assertEquals(ingredients.size(), expectedItems.size());
        for (int c = 0; c < ingredients.size(); c++) {
            final Map<String, String> expected = expectedItems.get(c);
            final Ingredient actual = ingredients.get(c);
            final DataTableChecker<Ingredient> checker = new DataTableChecker<>(expected, actual);
            assertAll(
                    () -> checker.assertEquals(e -> e, a -> a.getIngredientType().name(), "Type"),
                    () -> checker.assertEquals(e -> e, Ingredient::getName, "Name"),
                    checker::validateMapKeys
            );
        }
    }

}
