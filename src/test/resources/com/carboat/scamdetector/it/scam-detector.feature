Feature: Detect scam adverts

  Scenario: A non scam advert is allowed to pass
    Given a non scam advert
    When I launch the scam detector
    Then there are no errors

  Scenario: An advert with one error is reported
    Given an advert with an invalid firstname
    When I launch the scam detector
    Then I can see the error

  Scenario: An advert with many errors is reported
    Given an advert with all of the rules broken
    When I launch the scam detector
    Then I can see the errors