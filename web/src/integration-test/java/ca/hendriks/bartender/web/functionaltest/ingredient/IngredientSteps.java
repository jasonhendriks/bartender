package ca.hendriks.bartender.web.functionaltest.ingredient;

import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.ingredient.IngredientType;
import ca.hendriks.bartender.web.functionaltest.DataTableChecker;
import ca.hendriks.bartender.web.functionaltest.DomainSpecificLanguage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
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
        dsl.ingredients.addIngredientViaApi(ingredient);
    }

    @When("the administrator adds {string} into category {string} via the web browser,")
    public void the_administrator_adds_into_category_via_the_web_browser(final String name, final String type) {
        dsl.ingredients.addIngredientViaBrowser(name, type);
    }

    @When("the administrator updates {string} with category {string}")
    public void the_administrator_updates_with_category(final String ingredientName, final String type) {
        final IngredientType ingredientType = IngredientType.valueOf(type);
        dsl.ingredients.updateIngredient(ingredientName, ingredientType);
    }

    @Then("the available ingredients should be:")
    public void the_ingredients_should_be(final DataTable dataTable) {
        final List<Map<String, String>> expectedIngredients = dataTable.asMaps();
        final List<Ingredient> actualIngredients = dsl.ingredients.findIngredientsViaApi();
        verifyIngredients(expectedIngredients, actualIngredients);
    }

    @Then("the ingredients shown to the user are:")
    public void the_ingredients_shown_to_the_user_are(final DataTable dataTable) {
        final List<Map<String, String>> expectedIngredients = dataTable.asMaps();
        final List<Ingredient> actualIngredients = dsl.ingredients.retrieveIngredientsFromMockMvcResponse();
        verifyIngredients(expectedIngredients, actualIngredients);
    }

    private void verifyIngredients(final List<Map<String, String>> expectedItems, final List<Ingredient> ingredients) {
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

    @After
    public void cleanup() {
        dsl.ingredients.cleanUpRepository();
    }

}
