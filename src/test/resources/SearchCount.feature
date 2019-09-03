Feature: Search count
@ok
  Scenario Outline: Search via UI, API and DB and compare results
    Given I log in to schoolData
    When I search <Query> via UI and count institutions
    And I search <Query> via API and count institutions
    And I search <Query> via DB and count institutions
    Then Institutions count from UI, API and DB are the same
    Examples:
      | Query  |
      | Alaska |
      | Nevada |
      | Texas  |