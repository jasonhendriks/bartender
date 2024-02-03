package ca.hendriks.bartender.web.ingredient;

import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.ingredient.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientsApiController {

    private final IngredientRepository ingredientRepository;

    public IngredientsApiController(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient addIngredient(@RequestBody final Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @PutMapping("/{id}")
    public void updateIngredient(@PathVariable int id, @RequestBody Ingredient ingredientToUpdate){
        final Ingredient newIngredient = new Ingredient(id, ingredientToUpdate.getName(), ingredientToUpdate.getType());
        ingredientRepository.save(newIngredient);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@RequestBody Object ingredientName){
        final int ingredientIdToBeDeleted = ingredientRepository.findByName(ingredientName.toString()).getId();
        ingredientRepository.deleteById(ingredientIdToBeDeleted);
    }

}
