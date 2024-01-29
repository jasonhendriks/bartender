package ca.hendriks.bartender.web.ingredient;

import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.ingredient.IngredientRepository;
import ca.hendriks.bartender.drinks.ingredient.IngredientType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientsRestController {

    private final IngredientRepository ingredientRepository;

    public IngredientsRestController(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    @RequestMapping("/ingredients")
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/ingredients/add")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("/ingredients/update")
    public void updateIngredient(@RequestBody List<String> ingredientChangeList){
        final int ingredientToBeUpdatedId = ingredientRepository.findByName(ingredientChangeList.get(0)).getId();
        final IngredientType updatedIngredientType = IngredientType.valueOf(ingredientChangeList.get(3));
        final Ingredient updatedIngredient = new Ingredient(ingredientToBeUpdatedId, ingredientChangeList.get(2), updatedIngredientType);
        ingredientRepository.save(updatedIngredient);
    }

}
