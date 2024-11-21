package ca.hendriks.bartender.drinks.ingredient;

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
    private IngredientType type;

    public Ingredient() {
        // JSR 338: The entity class must have a no-arg constructor.
    }

    public Ingredient(final String name, final IngredientType type) {
        this(null, name, type);
    }

    public Ingredient(final Integer id, final String name, final IngredientType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public IngredientType getType() {
        return type;
    }

}
