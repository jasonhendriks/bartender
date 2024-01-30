package ca.hendriks.bartender.web.ingredient;

import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.ingredient.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/ingredients", produces = MediaType.TEXT_HTML_VALUE)
public class IngredientsPageController {

    private final IngredientRepository ingredientRepository;

    public IngredientsPageController(final IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showIngredientsPage(final Model model, @AuthenticationPrincipal final OidcUser principal) {
        addIngredients(model);
        return "ingredients";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String addIngredient(@RequestBody Ingredient ingredient, final Model model) {
        ingredientRepository.save(ingredient);
        addIngredients(model);
        return "ingredients";
    }

    private void addIngredients(final Model model) {
        model.addAttribute("ingredients", ingredientRepository.findAll());
    }

}
