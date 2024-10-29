Feature: Create New Order functionality

  Scenario: Selecting target branch
    When User clicks on create new order option
    And User selects target branch
    And User clicks on next
    Then User should be navigated to targeted branch


