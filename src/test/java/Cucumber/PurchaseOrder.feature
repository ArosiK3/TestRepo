Feature: Purchase the order from E-Commerce
  I want to use this template for my feature file

  Background: 
    Given I landed on Ecommerce page

  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productname> to cart
    And Checkout and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed

    Examples: 
      | username      | password  | productname   |
      | akk@test1.com | Salu@0987 | IPHONE 13 PRO |
