Feature: Login Page

  Background: 
    Given User navigates to application login page | navigateToApp "Chrome" "scrum"

  @LoginPageTestcase1
  Scenario: verify userName field Password field and login button are displayed
  Then User validates userName field is visible | elementDisplayed "login.username"
  Then User validates Password field is visible | elementDisplayed "login.password"
  And User validates Login button is visible | elementDisplayed "login.loginBtn"
  
  @LoginPageTestcase2
  Scenario Outline: Verify user able to login with valid credentials
  And User enter userName | enterValue "login.username" <UserName>
  And User enter password | enterValue "login.password" <Password>
  And User click on loging button | clickElement "login.loginBtn"
  Then Validate that user lands on home page | elementDisplayed "login.dashboardText"
  
  Examples:
  | UserName | Password   |
  | "admin"  | "admin123" |
  
  @LoginPageTestcase3
  Scenario Outline: Verify user unable to login with invalid credentials
  When User enter userName | enterValue "login.username" <UserName>
  And User enter password | enterValue "login.password" <Password>
  And User click on loging button | clickElement "login.loginBtn"
  Then Validate error message showing Required | verifyMessage "login.invalidCredentials" <Msg>
  
  Examples:
  | UserName   | Password   | Msg                   |
  | "admin123" | "admin123" | "Invalid credentials" |
  
  @LoginPageTestcase4
  Scenario: Verify System displays REQUIRED error message when username field not provided
  When User enter password | enterValue "login.password" "admin123"
  And User click on loging button | clickElement "login.loginBtn"
  Then Validate error message showing Required | verifyMessage "login.usernameError" "Required"
  
  @LoginPageTestcase5
  Scenario: Verify System displays Required error message when password field not provided
    When User enter userName | enterValue "login.username" "admin"
    And User click on loging button | clickElement "login.loginBtn"
    Then User validate error message showing Required | verifyMessage "login.passwordError" "Required"
