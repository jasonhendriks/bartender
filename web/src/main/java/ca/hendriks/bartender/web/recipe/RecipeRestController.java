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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void putRecipe(@PathVariable final int id, @RequestBody final Recipe updatedRecipe){
        Recipe recipeToBeUpdated = new Recipe(id, updatedRecipe.getName(), updatedRecipe.getIngredients(), updatedRecipe.getMethod());
        recipeRepository.save(recipeToBeUpdated);
    }

}
