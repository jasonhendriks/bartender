package ca.hendriks.bartender.web.inventory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsRestController {

    private final IngredientRepository ingredientRepository;

    public IngredientsRestController(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public List<Ingredient> findAllIngredients() {
        return ingredientRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

}
