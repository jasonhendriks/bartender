package ca.hendriks.bartender.web.functionaltest;

import ca.hendriks.bartender.common.IngredientVO;
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
        final IngredientVO ingredient = new IngredientVO(type, name);
        dsl.ingredients.addIngredient(ingredient);
    }

    @Then("the ingredients should be:")
    public void the_ingredients_should_be(final DataTable dataTable) {
        final List<IngredientVO> ingredients = dsl.ingredients.findIngredients();
        final List<Map<String, String>> expectedItems = dataTable.asMaps();
        assertEquals(ingredients.size(), expectedItems.size());
        for (int c = 0; c < ingredients.size(); c++) {
            final Map<String, String> expected = expectedItems.get(c);
            final IngredientVO actual = ingredients.get(c);
            final DataTableChecker<IngredientVO> checker = new DataTableChecker<>(expected, actual);
            assertAll(
                    () -> checker.assertEquals(e -> e, IngredientVO::type, "Type"),
                    () -> checker.assertEquals(e -> e, IngredientVO::name, "Name"),
                    () -> checker.validateMapKeys()
            );
        }
    }

}
