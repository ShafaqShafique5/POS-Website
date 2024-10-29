Feature: View dashboard functionality

  Background:
    When User clicks on dashboard option

  Scenario: User can view all branches data
    When User selects all branches through dropdown
    And User selects sales trend filters
    And User selects order trend filters
    Then User will be able to view sales statistics of all branches

  Scenario: User can view specific branch data
    When User selects specific branch through dropdown
    And User selects sales trend filters
    And User selects order trend filters
    Then User will be able to view sales statistics of specific branch
