Feature: Create player
  Create player with unique name

  Scenario: Should create new player
    Given Create player command with unique name
    When Invoke create player handler
    Then New player was created
    And Player created event was emitted


  Scenario: Should not create new player because player name exist
    Given Create player command with existing name
    When Invoke create player handler
    Then New player was not created
    And Player created event was not emitted
