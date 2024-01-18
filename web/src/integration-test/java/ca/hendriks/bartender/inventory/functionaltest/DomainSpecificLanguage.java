package ca.hendriks.bartender.inventory.functionaltest;

import ca.hendriks.bartender.common.Ingredient;
import ca.hendriks.bartender.web.inventory.InventoryRestController;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainSpecificLanguage {

    private final InventoryRestController inventoryRestController;

    DomainSpecificLanguage(final InventoryRestController inventoryRestController) {
        this.inventoryRestController = inventoryRestController;
    }

    public void addIngredient() {
        inventoryRestController.addIngredient();
    }

    public List<Ingredient> findIngredients() {
        return inventoryRestController.findIngredients();
    }

}
