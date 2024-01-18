package ca.hendriks.bartender.web.inventory;

import ca.hendriks.bartender.common.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller("/ingredient")
public class InventoryRestController {

    private final List<Ingredient> ingredients = new ArrayList<>();

    @PostMapping
    public void addIngredient() {
        final Ingredient ingredient = new Ingredient("Spirits", "Vodka");
        ingredients.add(ingredient);
    }

    @GetMapping
    public List<Ingredient> findIngredients() {
        return ingredients;
    }

}
