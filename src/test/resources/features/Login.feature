Feature: Login Functionality

  Scenario: Successful login with valid credentials
    When User enter valid credentials
    Given User is on the login page
    And User click on the login button
    Then User should be navigated to the home page


