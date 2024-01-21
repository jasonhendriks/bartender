package ca.hendriks.bartender.drinks.recipe.ingredient;

import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.recipe.RecipeBuilder;
import ca.hendriks.bartender.drinks.recipe.UnitType;

public class IngredientQuantityBuilder {

    private final RecipeBuilder recipeBuilderParent;

    private Float quantity;
    private UnitType unitType;
    private Ingredient ingredient;

    public IngredientQuantityBuilder() {
        this(null);
    }

    public IngredientQuantityBuilder(final RecipeBuilder recipeBuilder) {
        this.recipeBuilderParent = recipeBuilder;
    }

    public IngredientQuantityBuilder quantity(final Float quantity) {
        this.quantity = quantity;
        return this;
    }

    public IngredientQuantityBuilder unitType(final UnitType unitType) {
        this.unitType = unitType;
        return this;
    }

    public IngredientQuantityBuilder ingredient(final Ingredient ingredient) {
        this.ingredient = ingredient;
        return this;
    }

    public IngredientWithQuantity build() {
        return new IngredientWithQuantity(quantity, unitType, ingredient);
    }
}
