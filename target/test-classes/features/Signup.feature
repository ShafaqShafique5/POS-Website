@signup
Feature: User Signup

  Scenario: User signs up successfully
    Given user is on the ZAP OTT homepage
    When user signs up with valid credentials
    Then signup should be successful
