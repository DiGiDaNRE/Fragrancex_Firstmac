Feature: Navigational functionality of Frangrancex TOP PICK FOR YOU category

  Scenario: User orders Third product displayed on TOP PICKS FOR YOU category
    Given User access "https://www.fragrancex.com" website
    When In homepage direct to TOP PICKS FOR YOU category to select Third product listed
    Then Select second variant proceed with checkout
    Then Validate quantity is 1
    Then Changes quantity to 5