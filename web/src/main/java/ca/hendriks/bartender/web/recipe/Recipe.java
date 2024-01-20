package ca.hendriks.bartender.web.recipe;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String name;

    @OneToMany
    private Set<IngredientQuantity> ingredients;

    private String method;

    Recipe() {

    }

    public Recipe(final String name, final Set<IngredientQuantity> ingredients, final String method) {
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public Set<IngredientQuantity> getIngredients() {
        return ingredients;
    }

    public String getMethod() {
        return method;
    }

}
