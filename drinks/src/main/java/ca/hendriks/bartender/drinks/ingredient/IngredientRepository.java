package ca.hendriks.bartender.drinks.ingredient;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

    Ingredient findByName(String ingredientName);

}
