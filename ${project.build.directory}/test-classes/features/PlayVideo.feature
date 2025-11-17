@playVideo
Feature: Search and Play Show

  Scenario: User searches for a show and plays the video
    When user searches for "Hangor S-131"
    When user selects the show "Hangor S-131" from results
    Then the video should be playing
