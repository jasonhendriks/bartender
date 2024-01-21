package ca.hendriks.bartender.web.functionaltest.recipe;

import ca.hendriks.bartender.web.functionaltest.DomainSpecificLanguage;
import ca.hendriks.bartender.web.recipe.Recipe;
import ca.hendriks.bartender.web.recipe.RecipeBuilder;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RecipeSteps {

    private DomainSpecificLanguage dsl;

    @When("an administrator adds ingredients for a recipe named {string}:")
    public void an_administrator_updates_the_ingredients_for_to(final String recipeName, final DataTable dataTable) {
        final Recipe recipe = new RecipeBuilder()
                .name(recipeName)
                .build();
        dsl.recipes.addRecipe(recipe);
    }

    @When("an administrator adds ingredients for a recipe named {string} with method {string}:")
    public void an_administrator_adds_ingredients_for_a_recipe_named_with_method(String recipeName, String recipeMethod, final DataTable dataTable) {
//        final Recipe recipe = new RecipeBuilder()
//                .name(recipeName)
//                .method(recipeMethod)
//                .build();
//        dsl.recipes.addRecipe(recipe);
    }


    @Then("there is {int} recipe in the system.")
    public void there_is_recipe_in_the_system(Integer int1) {
    }

}
