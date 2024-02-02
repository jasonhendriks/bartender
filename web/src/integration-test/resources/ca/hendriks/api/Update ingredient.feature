Feature: Update ingredient

  In order for recipes and user inventories to reference ingredients
  As an administrator
  I want to be able to update an ingredient

  Scenario: The administrator updates an ingredient name

    Given ingredients:
      | Name    | Type    |
      | Dry Jin | SPIRITS |

    When the administrator updates "Dry Jin" with category "SPIRITS" to "Dry Gin" with "SPIRITS"

    Then the available ingredients should be:
      | Name    | Type    |
      | Dry Gin | SPIRITS |


  Scenario: The administrator updates an ingredient category

    Given ingredients:
      | Name    | Type          |
      | Whiskey | NON_ALCOHOLIC |

    When the administrator updates "Whiskey" with category "NON_ALCOHOLIC" to "Whiskey" with "SPIRITS"

    Then the available ingredients should be:
      | Name    | Type    |
      | Whiskey | SPIRITS |


  Scenario: The administrator updates an ingredient name and category

    Given ingredients:
      | Name    | Type          |
      | Rhum    | NON_ALCOHOLIC |

    When the administrator updates "Rhum" with category "NON_ALCOHOLIC" to "Rum" with "SPIRITS"

    Then the available ingredients should be:
      | Name    | Type    |
      | Rum     | SPIRITS |



