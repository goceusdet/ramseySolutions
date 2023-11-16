@US_01 @api @ui @regression
Feature: As a user I am able to add a sale item to my cart - API


  @TC_01 @api
  Scenario: Verify I'm able to get the Sale item list as user - API
    Given I specify a request to retrieve all Sale items
    And I specify a response to retrieve all Sale items
    When I send GET request to "graphql" endpoint
    Then fields are not empty
      | name |
      | id   |
    And number of Sale items is 20


  @TC_02 @api
  Scenario Outline: Verify I am able to add a Sale Item to my cart as user - API
    Given I send POST request to endpoint "/add" for "<itemName>" item and "<product_id>" id
    Then field "message" is "There is item in your cart."
    And response field "product_id" should match "<product_id>"
    And I delete the item "<product_id>" from cart

    Examples:
      | itemName                                      | product_id |
      | New! Questions for Humans: Christmas          | 788        |
      | Baby Steps Millionaires                       | 795        |
      | Questions for Humans: Middle School Classroom | 806        |


  @TC_03 @api
  Scenario Outline: Verify I am able to delete Sale item from my cart as user - API
    Given I send POST request to endpoint "/add" for "<itemName>" item and "<product_id>" id
    When I delete the item "<product_id>" from cart
    Then status code should be 204
    When I search for "<product_id>" item from API "/graphql" endpoint
    Then status code should be 404
    And GET response field "message" should match "Item not found"

    Examples:
      | itemName                                      | product_id |
      | New! Questions for Humans: Christmas          | 788        |
      | Baby Steps Millionaires                       | 795        |
      | Questions for Humans: Middle School Classroom | 806        |