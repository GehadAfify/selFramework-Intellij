# new feature
# Tags: optional

Feature: LOGIN Function

 Scenario Outline: User Can LOG IN nopCommerce website
    Given user in the home page
    When  i click on login link
    And  i enter "<email>","<password>"
    Then user login successfully
Examples:
   |email             |password|
   | test@gmail1.com | 123456 |
