package ca.hendriks.bartender.web.recipe;

import ca.hendriks.bartender.web.inventory.Ingredient;
import jakarta.persistence.*;

@Entity
public class IngredientQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column
    private final Float quantity;

    @Column
    @Enumerated(EnumType.STRING)
    private final UnitType unitType;

    @OneToOne
    private final Ingredient ingredient;

    public IngredientQuantity(final Float quantity, final UnitType unitType, final Ingredient ingredient) {
        this.quantity = quantity;
        this.unitType = unitType;
        this.ingredient = ingredient;
    }

}
