Feature: Add tournament
  As player with administration permission I want to add tournament game

  Scenario Outline: Add new tournament
    Given Add tournament command
    And With pot "<matches>" earnings
    And With tournament date "<occurrence>" last game
    When Invoke add tournament command handler
    Then Tournament was "<was_added>"
    And Add tournament event was "<was_emitted>".

    Examples:
      | matches     | occurrence | was_added | was_emitted |
      | matches     | after      | added     | emitted     |
      | matches     | before     | not added | not emitted |
      | not matches | after      | not added | not emitted |
      | not matches | before     | not added | not emitted |


