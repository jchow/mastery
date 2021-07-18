Feature: Inventory RESTFUL API

  Scenario: Create a product

    Given user wants to create a product with the following attributes
      | name  | category | sub-category | quantity |
      | slipper | clothes    |  shoe   | 101  |

    When user send 'CREATE' to RESTFUL API
    Then it returns 'IS SUCCESSFUL'


  Scenario: View a product

    Given user wants to view a product with id 1

    When user send 'VIEW' to RESTFUL API
    Then it returns 'IS SUCCESSFUL'

  Scenario: View all product

    When user send 'VIEW ALL' to RESTFUL API
    Then it returns 'IS SUCCESSFUL'

  Scenario: Delete a product

    Given user wants to delete a product with id 2

    When user send 'DELETE' to RESTFUL API
    Then it returns 'IS SUCCESSFUL'

