
Feature: Purchase the order from Ecommerce website

Background: 
Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given logged in with username <name> and password <passsword>
    When I add product <productName> from cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | name  							| password 		| productName |
      | ramlal@example.com 	| ramlalGR7  	| ZARA COAT 3	|
      
