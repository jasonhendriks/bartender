package ca.hendriks.bartender.drinks.recipe;

import ca.hendriks.bartender.drinks.recipe.ingredient.IngredientWithQuantity;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<IngredientWithQuantity> ingredients;

    private String method;

    Recipe() {
        // JSR 338: The entity class must have a no-arg constructor.
    }

    public Recipe(final String name, final Set<IngredientWithQuantity> ingredients, final String method) {
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public Set<IngredientWithQuantity> getIngredients() {
        return ingredients;
    }

    public String getMethod() {
        return method;
    }

}
