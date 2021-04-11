Feature: Create player
  Administrator try create new player

  Scenario Outline: Create new player with unique name
    Given Administrator type player name
    And Player name "<is_unique>"
    When Administrator save player
    Then Player "<was_saved>"

    Examples:
      | is_unique     | was_saved     |
      | is unique     | was saved     |
      | is not unique | was not saved |

