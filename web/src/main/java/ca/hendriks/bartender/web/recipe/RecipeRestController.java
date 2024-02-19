package ca.hendriks.bartender.web.recipe;

import ca.hendriks.bartender.drinks.recipe.Recipe;
import ca.hendriks.bartender.drinks.recipe.RecipeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeRestController {

    private final RecipeRepository recipeRepository;

    public RecipeRestController(final RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Recipe> getRecipes(){
        return recipeRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe addIngredient(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

}
