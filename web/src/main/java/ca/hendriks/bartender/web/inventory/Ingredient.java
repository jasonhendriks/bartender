package ca.hendriks.bartender.web.inventory;

import jakarta.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private IngredientType ingredientType;

    public Ingredient() {

    }

    public Ingredient(final Integer id, final String name, final IngredientType ingredientType) {
        this.id = id;
        this.name = name;
        this.ingredientType = ingredientType;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IngredientType getIngredientType() {
        return ingredientType;
    }

}
