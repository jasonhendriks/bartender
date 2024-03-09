Feature: Update recipe

  In order for recipes to be altered according to ingredients and methods available,
  As an administrator
  I want to update a recipe

  Scenario: The administrator updates a recipe's method

    Given a recipe named "Rum and Coke" with method "Stir and serve in a highball glass"

    When the administrator updates the recipe named "Rum and Coke" with method "Stir and serve in a highball glass" to "Rum and Coke" with "Pour in a glass with ice"

    Then the recipe should be named "Rum and Coke" with method "Pour in a glass with ice"


  Scenario: The administrator updates a recipe's name

    Given a recipe named "Rum and Coke" with method "Stir and serve in a highball glass"

    When the administrator updates the recipe named "Rum and Coke" with method "Stir and serve in a highball glass" to "Rum and Cola" with "Stir and serve in a highball glass"

    Then the recipe should be named "Rum and Cola" with method "Stir and serve in a highball glass"
