package ca.hendriks.bartender.web.recipe;

import ca.hendriks.bartender.web.inventory.Ingredient;

public class IngredientBuilder {

    private final RecipeBuilder recipeBuilderParent;

    private Float quantity;
    private UnitType unitType;

    public IngredientBuilder(final RecipeBuilder recipeBuilder) {
        this.recipeBuilderParent = recipeBuilder;
    }

    public IngredientBuilder quantity(final Float quantity) {
        this.quantity = quantity;
        return this;
    }

    public IngredientBuilder unitType(final UnitType unitType) {
        this.unitType = unitType;
        return this;
    }

    public RecipeBuilder ingredient(final Ingredient ingredient) {
        final IngredientQuantity ingredientQuantity = new IngredientQuantity(quantity, unitType, ingredient);
        recipeBuilderParent.ingredient(ingredientQuantity);
        return recipeBuilderParent;
    }

}
