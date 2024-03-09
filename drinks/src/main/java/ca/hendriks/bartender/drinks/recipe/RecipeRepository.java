package ca.hendriks.bartender.drinks.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    Recipe findByName(final String name);
}
