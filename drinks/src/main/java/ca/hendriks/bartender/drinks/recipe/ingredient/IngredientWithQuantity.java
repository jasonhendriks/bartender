package ca.hendriks.bartender.drinks.recipe.ingredient;

import ca.hendriks.bartender.drinks.ingredient.Ingredient;
import ca.hendriks.bartender.drinks.recipe.UnitType;
import jakarta.persistence.*;

@Entity
public class IngredientWithQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private Float quantity;

    @Column
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @OneToOne
    private Ingredient ingredient;

    IngredientWithQuantity() {
        // JSR 338: The entity class must have a no-arg constructor.
    }

    public IngredientWithQuantity(final Float quantity, final UnitType unitType, final Ingredient ingredient) {
        this.quantity = quantity;
        this.unitType = unitType;
        this.ingredient = ingredient;
    }

}
