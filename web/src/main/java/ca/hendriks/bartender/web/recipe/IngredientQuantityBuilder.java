package ca.hendriks.bartender.web.recipe;

import ca.hendriks.bartender.web.inventory.Ingredient;

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

    public IngredientQuantity build() {
        return new IngredientQuantity(quantity, unitType, ingredient);
    }
}
