package ca.hendriks.bartender.web.recipe;

import ca.hendriks.bartender.web.inventory.Ingredient;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class RecipeBuilderTest {

    @Test
    void testBuildingARecipe() {
        final IngredientQuantity ingredient = new IngredientQuantityBuilder()
                .quantity(1f)
                .unitType(UnitType.MEASURE)
                .ingredient(mock(Ingredient.class))
                .build();
        final Recipe actual = new RecipeBuilder()
                .name("vodka")
                .method("shaken, not stirred")
                .ingredients(ingredient)
                .build();
        assertAll(
                () -> assertEquals("vodka", actual.getName()),
                () -> assertEquals("shaken, not stirred", actual.getMethod())
        );
    }

}
