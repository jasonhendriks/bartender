Feature: Update ingredient

  In order for recipes and user inventories to reference ingredients
  As an administrator
  I want to be able to update an ingredient

  Scenario: The administrator updates an ingredient

    Given ingredients:
      | Name    | Type          |
      | Whiskey | NON_ALCOHOLIC |

    When the administrator updates "Whiskey" with category "SPIRITS"

    Then the available ingredients should be:
      | Name    | Type    |
      | Whiskey | SPIRITS |


