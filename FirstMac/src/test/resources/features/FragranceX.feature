Feature: User orders from TOP PICK FOR YOU category

  Scenario: User orders Third product displayed
    Given User access website
    When User naavigate to TOP PICK FOR YOU category to select Third product displayed
    Then Select second variant proceed with checkout
    Then Validate quantity is one
    Then Changes quantity to five