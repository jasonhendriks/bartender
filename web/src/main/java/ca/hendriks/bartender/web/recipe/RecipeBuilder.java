package ca.hendriks.bartender.web.recipe;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RecipeBuilder {

    private String name;
    private final Set<IngredientQuantity> ingredients;
    private String method;

    public RecipeBuilder() {
        ingredients = new HashSet<>();
    }

    public RecipeBuilder name(final String recipeName) {
        name = recipeName;
        return this;
    }

    public Recipe build() {
        return new Recipe(name, ingredients, method);
    }

    public RecipeBuilder ingredients(final IngredientQuantity ingredient) {
        this.ingredients.add(ingredient);
        return this;
    }

    public RecipeBuilder ingredients(final Collection<IngredientQuantity> ingredients) {
        this.ingredients.addAll(ingredients);
        return this;
    }

    public RecipeBuilder method(final String method) {
        this.method = method;
        return this;
    }

    public RecipeBuilder from(final Recipe recipe) {
        name(recipe.getName());
        method(recipe.getMethod());
        ingredients.addAll(recipe.getIngredients());
        return this;
    }

}
