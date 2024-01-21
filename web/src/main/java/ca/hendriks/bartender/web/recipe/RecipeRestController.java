package ca.hendriks.bartender.web.recipe;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipes")
public class RecipeRestController {

    private final RecipeRepository recipeRepository;

    public RecipeRestController(final RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe addIngredient(@RequestBody Recipe recipe) {
        return recipeRepository.save(recipe);
    }

}
