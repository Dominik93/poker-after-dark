Feature: Add game validator
  Add game validator

  Scenario: Should pass pot validation because pot sum to 0
    Given Participants with pot sum to zero
    When Validate participations
    Then Validation pass

  Scenario: Should fail pot validation because pot not sum to 0
    Given Participants with pot not sum zero
    When Validate participations
    Then Validation failed
