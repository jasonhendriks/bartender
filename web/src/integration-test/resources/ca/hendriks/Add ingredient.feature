Feature: Add ingredient

  In order for recipes and user inventories to reference ingredients
  As an administrator
  I want to add an ingredient (as either Spirits, Non-Alcoholic, Syrups, Bitters)

  Scenario: The administrator adds an ingredient

    When the administrator adds "Vodka" into categrory "Spirits",

    Then the ingredients should be:
      | Type    | Name  |
      | Spirits | Vodka |
