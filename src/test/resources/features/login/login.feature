@Test1
Feature: login saucedemo

  Scenario: success login saucedemo
    Given user visit saucedemo
    When user login with credential
    |username     | password    |
    |standard_user| secret_sauce|
    Then user will redirect to homepage and see text "Swag Labs"

  Scenario: user login with wrong password
      Given user visit saucedemo
      When user login with credential
        |username     | password    |
        |standard_user| salah password|
      Then user verify "Epic sadface: Username and password do not match any user in this service"

  Scenario: user login with wrong username
    Given user visit saucedemo
    When user login with credential
      |username     | password    |
      |standard     | secret_sauce|
    Then user verify "Epic sadface: Username and password do not match any user in this service"

  Scenario: user login with number
    Given user visit saucedemo
    When user login with credential
      |username     | password    |
      |08912122     | secret_sauce|
    Then user verify "Epic sadface: Username and password do not match any user in this service"

  Scenario: user login with symbol
    Given user visit saucedemo
    When user login with credential
      |username     | password    |
      |@@@@@@@@     | secret_sauce|
    Then user verify "Epic sadface: Username and password do not match any user in this service"

  Scenario: user login with password symbol
    Given user visit saucedemo
    When user login with credential
      |username     | password    |
      |standard_user| @@@@@@@@@@  |
    Then user verify "Epic sadface: Username and password do not match any user in this service"

  Scenario: user login with account locked out
    Given user visit saucedemo
    When user login with credential
      |username       | password    |
      |locked_out_user| secret_sauce|
    Then user verify "Epic sadface: Sorry, this user has been locked out."