package ca.hendriks.bartender.web.inventory;

import ca.hendriks.bartender.common.Ingredient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryRestController {

    private final List<Ingredient> ingredients = new ArrayList<>();

    @GetMapping
    public List<Ingredient> findAllIngredients() {
        return ingredients;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addIngredient() {
        final Ingredient ingredient = new Ingredient("Spirits", "Vodka");
        ingredients.add(ingredient);
    }

}
