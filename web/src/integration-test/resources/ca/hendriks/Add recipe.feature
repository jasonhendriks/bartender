Feature: Add recipe

  In order for recipes to be matched to user's stock,
  As an administrator
  I want to add a recipe

  Scenario: An administrator add a recipe

    Given ingredients:
      | Name              | Type          |
      | Dry Gin           | SPIRITS       |
      | Kina Lillet       | WINE          |
      | Orange Juice      | NON_ALCOHOLIC |
      | Angostura Bitters | BITTERS       |

    When an administrator adds ingredients for a recipe named "The Abbey Cocktail" with method "Shake well and strain into cocktail glass":
      | Quantity | Unit    | Ingredient        |
      | 1/2      | MEASURE | Dry Gin           |
      | 1/4      | MEASURE | Kina Lillet       |
      | 1/4      | MEASURE | Orange juice      |
      | 1        | DASH    | Angostura Bitters |

    Then there is 1 recipe in the system.
