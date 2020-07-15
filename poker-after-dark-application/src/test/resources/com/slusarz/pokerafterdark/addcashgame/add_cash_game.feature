Feature: Add cash game
  Add cash game

  Scenario: Should add new cash game with matching earnings and skip validation
    Given Add game command
    And Pot matches earnings
    And With date "after" last game
    And With skip validation set to "true"
    When Invoke add game handler
    Then Game was added
    And Add game event was emitted

  Scenario: Should add new cash game with not matching earnings and skip validation
    Given Add game command
    And Pot not matches earnings
    And With date "after" last game
    And With skip validation set to "true"
    When Invoke add game handler
    Then Game was added
    And Add game event was emitted


  Scenario: Should add new cash game with matching earnings and not skip validation
    Given Add game command
    And Pot matches earnings
    And With date "after" last game
    And With skip validation set to "false"
    When Invoke add game handler
    Then Game was added
    And Add game event was emitted


  Scenario: Should add new cash game with not matching earnings and not skip validation
    Given Add game command
    And Pot not matches earnings
    And With date "after" last game
    And With skip validation set to "false"
    When Invoke add game handler
    Then Game was not added
    And Add game event was not emitted

  Scenario: Should add new cash game with matching earnings and skip validation
    Given Add game command
    And Pot matches earnings
    And With date "before" last game
    And With skip validation set to "true"
    When Invoke add game handler
    Then Game was not added
    And Add game event was not emitted

  Scenario: Should add new cash game with not matching earnings and skip validation
    Given Add game command
    And Pot not matches earnings
    And With date "before" last game
    And With skip validation set to "true"
    When Invoke add game handler
    Then Game was not added
    And Add game event was not emitted


  Scenario: Should add new cash game with matching earnings and not skip validation
    Given Add game command
    And Pot matches earnings
    And With date "before" last game
    And With skip validation set to "false"
    When Invoke add game handler
    Then Game was not added
    And Add game event was not emitted


  Scenario: Should add new cash game with not matching earnings and not skip validation
    Given Add game command
    And Pot not matches earnings
    And With date "before" last game
    And With skip validation set to "false"
    When Invoke add game handler
    Then Game was not added
    And Add game event was not emitted