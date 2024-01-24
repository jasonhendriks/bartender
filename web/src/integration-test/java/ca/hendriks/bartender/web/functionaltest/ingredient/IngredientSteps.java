package ca.hendriks.bartender.web.functionaltest.ingredient;

import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.ingredient.IngredientType;
import ca.hendriks.bartender.web.functionaltest.DataTableChecker;
import ca.hendriks.bartender.web.functionaltest.DomainSpecificLanguage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientSteps {

    public static final String TYPE = "Type";
    public static final String NAME = "Name";

    private final DomainSpecificLanguage dsl;

    public IngredientSteps(final DomainSpecificLanguage dsl) {
        this.dsl = dsl;
    }

    @Given("ingredients:")
    public void givenIngredients(final DataTable dataTable) {
        final List<Ingredient> ingredients = dataTable.asMaps()
                .stream()
                .map(x -> {
                    final String name = x.get(NAME);
                    final IngredientType type = IngredientType.valueOf(x.get(TYPE));
                    return new Ingredient(null, name, type);
                })
                .collect(Collectors.toList());
        dsl.ingredients.givenIngredients(ingredients);
    }

    @When("the administrator adds {string} into category {string},")
    public void the_administrator_adds_into_category(final String name, final String type) {
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
                    () -> checker.assertEquals(e -> e, a -> a.getIngredientType().name(), TYPE),
                    () -> checker.assertEquals(e -> e, Ingredient::getName, NAME),
                    checker::validateMapKeys
            );
        }
    }

}
