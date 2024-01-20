package ca.hendriks.bartender.web.functionaltest;

import ca.hendriks.bartender.web.functionaltest.ingredient.BddIngredientService;
import ca.hendriks.bartender.web.functionaltest.recipe.BddRecipeService;
import org.springframework.stereotype.Service;

@Service
public class DomainSpecificLanguage {

    public final BddMockMvcService mockMvc;
    public final BddIngredientService ingredients;
    public final BddRecipeService recipes;

    DomainSpecificLanguage(final BddMockMvcService mockMvc, final BddIngredientService bddIngredientService, final BddRecipeService recipes) {
        this.mockMvc = mockMvc;
        this.ingredients = bddIngredientService;
        this.recipes = recipes;
    }

}
