package ca.hendriks.bartender.web.inventory;

import ca.hendriks.bartender.common.IngredientVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientsRestController {

    private final List<IngredientVO> ingredients = new ArrayList<>();

    @GetMapping
    public List<IngredientVO> findAllIngredients() {
        return ingredients;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientVO addIngredient(@RequestBody IngredientVO ingredient) {
        ingredients.add(ingredient);
        return ingredient;
    }

}
