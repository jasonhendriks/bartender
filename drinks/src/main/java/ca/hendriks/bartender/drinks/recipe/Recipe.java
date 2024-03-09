package ca.hendriks.bartender.drinks.recipe;

import ca.hendriks.bartender.drinks.recipe.ingredient.IngredientWithQuantity;
import jakarta.persistence.*;

import java.util.Objects;
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

    public Recipe(Integer id, String name, Set<IngredientWithQuantity> ingredients, String method) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
    }

    public Integer getId(){ return id; }

    public String getName() {
        return name;
    }

    public Set<IngredientWithQuantity> getIngredients() {
        return ingredients;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(name, recipe.name) && Objects.equals(ingredients, recipe.ingredients) && Objects.equals(method, recipe.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ingredients, method);
    }
}
