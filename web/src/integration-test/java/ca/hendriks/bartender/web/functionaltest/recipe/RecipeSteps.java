package ca.hendriks.bartender.web.functionaltest.recipe;

import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.recipe.Recipe;
import ca.hendriks.bartender.drinks.recipe.RecipeBuilder;
import ca.hendriks.bartender.drinks.recipe.UnitType;
import ca.hendriks.bartender.drinks.recipe.ingredient.IngredientQuantityBuilder;
import ca.hendriks.bartender.drinks.recipe.ingredient.IngredientWithQuantity;
import ca.hendriks.bartender.web.functionaltest.DomainSpecificLanguage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeSteps {

    private final DomainSpecificLanguage dsl;

    public RecipeSteps(final DomainSpecificLanguage dsl) {
        this.dsl = dsl;
    }

    @When("an administrator adds ingredients for a recipe named {string} with method {string}:")
    public void an_administrator_add_ingredients_for_a_recipe(final String recipeName, final String method, final DataTable dataTable) {
        final List<IngredientWithQuantity> ingredients = dataTable.asMaps()
                .stream()
                .map(x -> {
                    final Float quantity = parseQuantity(x.get("Quantity"));
                    final UnitType type = UnitType.valueOf(x.get("Unit"));
                    final String ingredientName = x.get("Ingredient");
                    final Ingredient ingredient = dsl.ingredients.findIngredient(ingredientName);
                    return new IngredientQuantityBuilder()
                            .quantity(quantity)
                            .unitType(type)
                            .ingredient(ingredient)
                            .build();
                }).collect(Collectors.toList());
        final Recipe recipe = new RecipeBuilder()
                .name(recipeName)
                .method(method)
                .ingredients(ingredients)
                .build();
        dsl.recipes.addRecipe(recipe);
    }

    private Float parseQuantity(final String quantity) {
        switch (quantity) {
            case "1/2":
                return .5f;
            case "1/4":
                return .25f;
            default:
                return Float.parseFloat(quantity);
        }
    }

    @Then("there is {int} recipe in the system.")
    public void there_is_recipe_in_the_system(final Integer expectedRecipeCount) {
        List<Recipe> actualRecipes = dsl.recipes.getRecipes();
        assertEquals(expectedRecipeCount, actualRecipes.size());
    }

}
