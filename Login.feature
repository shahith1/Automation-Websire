Feature: Login functionality of PHPTravels website
  As a registered user

  @sanity @login
  Scenario: Successful login with valid credentials
    Given I am on the PHPTravels login page
    When I enter valid email and valid password
    And I click on the login button
    Then I should be redirected to the user dashboard
    And I should see the welcome message on the dashboard

  @regression @invalid
  Scenario: Login attempt with invalid password
    Given I am on the PHPTravels login page
    When I enter valid email and invalid password
    And I click on the login button
    Then I should see an error message indicating invalid credentials
