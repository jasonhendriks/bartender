Feature: Add ingredient to inventory

  Scenario: The user wants to add an ingredient to their inventory

    Given Vodka is an ingredient

    When user tries to add Vodka to their inventory

    Then the user's inventory should contain:
      | Ingredient |
      | Vodka      |
