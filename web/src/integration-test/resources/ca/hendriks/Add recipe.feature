Feature: Add recipe

  In order for recipes to be matched to user's stock,
  As an administrator
  I want to add a recipe

  Scenario: An administrator add a recipe

    When an administrator adds ingredients for a recipe named "The Abbey Cocktail" with method "Shake well and strain into cocktail glass":
      | Quantity | Unit    | Ingredient        |
      | 1/2      | measure | Dry Gin           |
      | 1/4      | measure | Kina Lillet       |
      | 1/4      | measure | Orange juice      |
      | 1        | dash    | Angostura Bitters |

    Then there is 1 recipe in the system.
