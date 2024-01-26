Feature: Find Recipe

  Background:

    Given ingredients:
      | Name              | Type          |
      | Dry Gin           | SPIRITS       |
      | Kina Lillet       | WINE          |
      | Orange Juice      | NON_ALCOHOLIC |
      | Angostura Bitters | BITTERS       |

    Given a recipe named "The Abbey Cocktail" with method "Shake well and strain into cocktail glass":
      | Quantity | Unit    | Ingredient        |
      | 1/2      | MEASURE | Dry Gin           |
      | 1/4      | MEASURE | Kina Lillet       |
      | 1/4      | MEASURE | Orange juice      |
      | 1        | DASH    | Angostura Bitters |

  Scenario:

    When searcing for recipes with these ingredients:

    Given ingredients:
      | Name              | Type          |
      | Dry Gin           | SPIRITS       |
      | Kina Lillet       | WINE          |
      | Orange Juice      | NON_ALCOHOLIC |
      | Angostura Bitters | BITTERS       |

    Then the search results are:
      | Name               |
      | The Abbey Cocktail |
