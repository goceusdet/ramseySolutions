@ui @regression @US_02
Feature: As a user I am able to add a sale item to my cart - UI


  Background: user navigates to Sale page
    Given user is on the "home" page
    When user navigates to "sale" page


  @TC_04
  Scenario: Verify user can get to the Sale item list
    #Given user is on the "home" page
    #When user navigates to "sale" page
    Then user is on the "sale" page
    And user should be able to see the Sale item list
      | New! Questions for Humans: Christmas                       |
      | New! Questions for Humans: Thanksgiving                    |
      | Know Yourself Money Assessment For Individuals             |
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


  @TC_05
  Scenario Outline: Verify user is able to add a Sale Item to cart
    #Given user is on the "home" page
    #When user navigates to "sale" page
    And user clicks on "<itemName>" item
    And user clicks Add to Cart button
    And user clicks on View Cart button
    Then item name "<itemName>" should be displayed
    And quantity value for "<itemName>" should be 1
    And user deletes "<itemName>" from cart

    Examples:
      | itemName                             |
      | New! Questions for Humans: Christmas |
      | Baby Steps Millionaires              |
      | From Paycheck to Purpose             |


  @TC_06
  Scenario Outline: Verify user can delete a Sale item from cart
    #Given user is on the "home" page
    #When user navigates to "sale" page
    Given user adds "<itemName>" item to cart
    When user clicks on View Cart button
    And user deletes "<itemName>" from cart
    Then item "<itemName>" shouldn't be present in cart

    Examples:
      | itemName                             |
      | New! Questions for Humans: Christmas |
      | Baby Steps Millionaires              |
      | From Paycheck to Purpose             |