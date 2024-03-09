package ca.hendriks.bartender.web.functionaltest.recipe;

import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.recipe.Recipe;
import ca.hendriks.bartender.drinks.recipe.RecipeBuilder;
import ca.hendriks.bartender.drinks.recipe.UnitType;
import ca.hendriks.bartender.drinks.recipe.ingredient.IngredientQuantityBuilder;
import ca.hendriks.bartender.drinks.recipe.ingredient.IngredientWithQuantity;
import ca.hendriks.bartender.web.functionaltest.DomainSpecificLanguage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RecipeSteps {

    private final DomainSpecificLanguage dsl;

    public RecipeSteps(final DomainSpecificLanguage dsl) {
        this.dsl = dsl;
    }

    @When("an administrator adds ingredients for a recipe named {string} with method {string}:")
    public void an_administrator_add_ingredients_for_a_recipe(final String recipeName, final String method, final DataTable dataTable) {
        final Set<IngredientWithQuantity> ingredients = extractIngredientsWIthQuantityFromDataTable(dataTable);
        final Recipe recipe = new RecipeBuilder()
                .name(recipeName)
                .method(method)
                .ingredients(ingredients)
                .build();
        dsl.recipes.addRecipe(recipe);
    }

    private Set<IngredientWithQuantity> extractIngredientsWIthQuantityFromDataTable(final DataTable dataTable) {
        return dataTable.asMaps()
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
                }).collect(Collectors.toSet());
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

    @Given("a recipe named {string} with method {string}")
    public void a_recipe_named_with_method(final String recipeName, final String recipeMethod) {
        Recipe givenRecipe = new Recipe(recipeName, null, recipeMethod);
        dsl.recipes.addRecipe(givenRecipe);
    }
    @When("the administrator updates the recipe named {string} with method {string} to {string} with {string}")
    public void the_administrator_updates_the_recipe_named_with_method_to_with(final String recipeName, final String recipeMethod,
                                                                               final String updatedRecipeName, final String updatedRecipeMethod) {
        Recipe originalRecipe = new Recipe(recipeName, null, recipeMethod);
        Recipe updatedRecipe = new Recipe(updatedRecipeName, null, updatedRecipeMethod);
        dsl.recipes.updateRecipe(originalRecipe, updatedRecipe);

    }
    @Then("the recipe should be named {string} with method {string}")
    public void the_recipe_should_be_named_with_method(final String expectedRecipeName, final String expectedRecipeMethod) {
        Recipe acutalRecipe = dsl.recipes.getRecipes().get(0);
        assertEquals(expectedRecipeName, acutalRecipe.getName());
        assertEquals(expectedRecipeMethod, acutalRecipe.getMethod());
    }

    @When("the administrator updates the recipe named {string} with method {string}:")
    public void the_administrator_updates_the_recipe_named_with_ingredient_to_with(final String recipeName, final String method, final DataTable dataTable) {
        final Recipe originalRecipe = getRecipe(recipeName);
        final Set<IngredientWithQuantity> ingredientsWithQuantity = extractIngredientsWIthQuantityFromDataTable(dataTable);
        final Recipe updatedRecipe = new Recipe(recipeName, ingredientsWithQuantity, method);
        dsl.recipes.updateRecipe(originalRecipe, updatedRecipe);

    }

    private Recipe getRecipe(final String recipeName) {
        final IngredientWithQuantity originalIngredientWithQuantity = new IngredientWithQuantity(null, null, null);
        return new Recipe(recipeName, new HashSet<>(Set.of(originalIngredientWithQuantity)), "");
    }

    @Then("the recipe should be named {string} with method {string} with ingredients:")
    public void the_recipe_should_be(final String expectedRecipeName, final String expectedRecipeMethod, final DataTable dataTable) {
        final Set<IngredientWithQuantity> expectedIngredients = extractIngredientsWIthQuantityFromDataTable(dataTable);
        final List<Recipe> expectedRecipes = constructExpectedRecipes(expectedRecipeName, expectedRecipeMethod, expectedIngredients);
        final List<Recipe> actualRecipes = dsl.recipes.getRecipes();

        verifyRecipesMatch(expectedRecipes, actualRecipes);

    }

    private List<Recipe> constructExpectedRecipes(final String expectedRecipeName, final String expectedRecipeMethod, final Set<IngredientWithQuantity> expectedIngredients) {
        final List<Recipe> expectedRecipes = new ArrayList<>();
        final Recipe expectedRecipe = new Recipe(expectedRecipeName, new HashSet<>(expectedIngredients), expectedRecipeMethod);
        expectedRecipes.add(expectedRecipe);
        return expectedRecipes;
    }

    private void verifyRecipesMatch(final List<Recipe> expectedRecipes, final List<Recipe> actualRecipes) {
        final Recipe expectedRecipe = expectedRecipes.get(0);
        final Recipe actualRecipe = actualRecipes.get(0);

        assertEquals(expectedRecipes.size(), actualRecipes.size());

        assertEquals(expectedRecipe.getName(), actualRecipe.getName());
        assertEquals(expectedRecipe.getMethod(), actualRecipe.getMethod());

        Set<IngredientWithQuantity> expectedRecipeIngredients = expectedRecipe.getIngredients();
        Set<IngredientWithQuantity> actualRecipeIngredients = actualRecipe.getIngredients();

        assertEquals(expectedRecipeIngredients.size(), actualRecipeIngredients.size());
        IngredientWithQuantity first = expectedRecipeIngredients.iterator().next();
        IngredientWithQuantity second = actualRecipeIngredients.iterator().next();
        assertEquals(first, second);
    }

    @After
    public void cleanup(){
        dsl.recipes.cleanup();
    }

}
