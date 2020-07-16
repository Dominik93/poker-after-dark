Feature: Add tournament
  Administrator try to add tournament game

  Scenario Outline: Add new tournament game
    Given Operator fill tournament form
    And With pot "<that_matches>" matches earnings
    And With tournament date "<occurrence>" last game
    When Administrator save tournament
    Then Tournament "<was_added>" added

    Examples:
      | that_matches | occurrence | was_added |
      | that         | after      | was       |
      | that         | before     | was not   |
      | that not     | after      | was not   |
      | that not     | before     | was not   |


