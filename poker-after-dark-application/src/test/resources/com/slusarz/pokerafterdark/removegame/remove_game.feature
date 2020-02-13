Feature: Remove game
  Remove game

  Scenario: Should remove game
    Given Remove game command with existing id
    When Invoke remove game handler
    Then Game was removed
    And Game removed event was emitted
