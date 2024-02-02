Feature: Add ingredient via browser

  In order for recipes and user inventories to reference ingredients
  As an administrator
  I want to add an ingredient (as either Spirits, Non-Alcoholic, Syrups, Bitters)

  Scenario: The administrator adds an ingredient

    When the administrator adds "Vodka" into category "SPIRITS" via the web browser,

    Then the ingredients shown to the user are:
      | Name  | Type    |
      | Vodka | SPIRITS |
