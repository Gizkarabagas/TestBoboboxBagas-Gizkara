@Test1
Feature: flow transaction

  Scenario: user success transaction
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user select items to cart
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
    And user move to cart
    And user checkout cart
    And user fill firstname "Bagas"
    And user fill lastname "gizkara"
    And user fill zipcode "45559"
    Then user successfully checkout and show "Thank you for your order!"

  Scenario: user click continue without fill information
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user select items to cart
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
    And user move to cart
    And user checkout cart
    And user continue information
    Then user verify "Error: First Name is required"


  Scenario: user login and no fill postal zip code in confirmation order
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user move to cart
    And user checkout cart
    And user fill firstname "Bagas"
    And user fill lastname "gizz"
    And user fill zipcode ""
    And user continue information
    Then user verify "Error: Postal Code is required"

  Scenario: user login and no fill First name in confirmation order
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user move to cart
    And user checkout cart
    And user fill firstname ""
    And user fill lastname "gizz"
    And user fill zipcode "4559"
    And user continue information
    Then user verify "Error: First Name is required"

  Scenario: user login and no last First name in confirmation order
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user move to cart
    And user checkout cart
    And user fill firstname "Bagas"
    And user fill lastname ""
    And user fill zipcode "4559"
    And user continue information
    Then user verify "Error: Last Name is required"

  Scenario: user want go to detail item
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user click item "Sauce Labs Bike Light"
    Then user successfully verify detail item "Sauce Labs Bike Light"

  Scenario: user in overiview and go to detail item
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user move to cart
    And user checkout cart
    And user fill firstname "Bagas"
    And user fill lastname "gizkara"
    And user fill zipcode "45559"
    And user continue information
    And user click item "Sauce Labs Bike Light"
    Then user successfully verify detail item "Sauce Labs Bike Light"

  Scenario: user check cart have several item
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user move to cart
    Then user verify on cart have "4" items

  Scenario: user in cart and go to detail item
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user move to cart
    And user click item "Sauce Labs Bolt T-Shirt"
    Then user successfully verify detail item "Sauce Labs Bolt T-Shirt"

  Scenario: user want to remove item from cart QTY and sucess transaction
    Given user visit saucedemo
    When user login with credential
      | username      | password     |
      | standard_user | secret_sauce |
    And user move to cart
    And user remove item from cart
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
    And user checkout cart
    And user fill firstname "Bagas"
    And user fill lastname "gizkara"
    And user fill zipcode "45559"
    Then user successfully checkout and show "Thank you for your order!"