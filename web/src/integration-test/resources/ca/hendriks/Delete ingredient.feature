#TODO - fix delete
#Feature: Delete ingredient
#
#  In order for recipes and user inventories to be accurate
#  As an administrator
#  I want to delete an ingredient
#
#  Scenario: The administrator deletes an ingredient
#
#    Given ingredients:
#      | Name              | Type          |
#      | Vodka             | SPIRITS       |
#      | Orange Juice      | NON_ALCOHOLIC |
#      | Angostura Bitters | BITTERS       |
#
#    When the administrator deletes "Vodka"
#
#    Then the available ingredients should be:
#      | Name              | Type          |
#      | Orange Juice      | NON_ALCOHOLIC |
#      | Angostura Bitters | BITTERS       |
