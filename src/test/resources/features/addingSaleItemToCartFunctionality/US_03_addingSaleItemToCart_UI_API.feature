@ui @api @regression @US_03
Feature: As a user I am able to add a sale item to my cart - UI / API validation

  Background: user navigates to Sale page
    Given user is on the "home" page
    When user navigates to "Sale" page

  @TC_07
  Scenario: Verify user can get to the Sale item list - UI / API
    #Given user is on the home page
    #When user navigates to "Sale" page
    And user sends GET request to "/graphql"
    Then product list names from API response should match with UI product list names
      | New! Questions for Humans: Christmas                       |
      | Know Yourself Money Assessment For Individuals"            |
      | New! Questions for Humans: Thanksgiving                    |
      | New! Questions For Humans Couples Second Edition           |
      | The Total Money Makeover                                   |
      | Get Clear Career Assessment - For Career Change            |
      | 2024 Ramsey Goal Planner                                   |
      | Questions For Humans: Friends Second Edition               |
      | Baby Steps Millionaires                                    |
      | New! Smart Saver Bank                                      |
      | New! Questions for Humans: Grandparents and Grandkids      |
      | New! Questions for Humans: Parents and Kids Second Edition |
      | Junior's Adventures Storytime Collection                   |
      | Own Your Past, Change Your Future                          |
      | Questions for Humans: Couples                              |
      | From Paycheck to Purpose                                   |
      | Questions for Humans: High School Classroom                |
      | Questions for Humans: Elementary Classroom                 |
      | Questions for Humans: Parents and Kids                     |


  @TC_08
  Scenario Outline: Verify user is able to add a Sale Item to cart - UI /API
    #Given user is on the home page
    #When user navigates to "Sale" page
    And user clicks on "<itemName>" item
    And user clicks Add to Cart button
    Then user clicks on View Cart button
    And user prepares API request with query param "name" as "<itemName>"
    And user sends GET request to "/cart" endpoint
    And item name "<itemName>" on UI should be same with "name" value from API
    And user deletes "<itemName>" item from cart

    Examples:
      | itemName                             |
      | New! Questions for Humans: Christmas |
      | Baby Steps Millionaires              |
      | From Paycheck to Purpose             |

  @TC_09
  Scenario Outline: Verify user can delete a Sale item from cart UI / API
    #Given user is on the home page
    #When user navigates to "Sale" page
    And user adds "<itemName>" item to cart
    And user clicks on View Cart
    And user decreases "<itemName>" item quantity value by 1
    And user prepares API request with query param "product_id" as "<product_id>"
    And user sends GET request to "/graphql" endpoint
    Then item "<itemName>" shouldn't be present in UI cart
    And response status code should be 404
    And response field "message" for deleted item should match "Item not found"

    Examples:
      | itemName                             | product_id |
      | New! Questions for Humans: Christmas | 788        |
      | Baby Steps Millionaires              | 795        |
      | From Paycheck to Purpose             | 806        |