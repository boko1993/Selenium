Feature: Login page
  Scenario Outline: Verify the username field, password field and login button displayed on login page
    Given User navigates to application | navigateToApp <browser> <region>
    Then User verifies username field is displayed | elementDisplayed "login.username"
    And User verifies password field is displayed | elementDisplayed "login.password"
    And User verifies login button is displayed | elementDisplayed "login.loginBtn"
    @scrum
    Examples: 
      | region  | browser  |
      | "scrum" | "chrome" |

  #Scenario Outline: Verify user able to login with valid credentials
    #Given User navigates to application | navigateToApp <browser> <region>
    #When User enters username | enterValue "login.username" <usernameValue>
    #And User enters password | enterValue "login.password" <passwordValue>
    #And User clicks login button | clickElement "login.loginBtn"
    #Then User is redirected to dashboard page | verifyPageTitle <pageTitle>
#
    #@scrum
    #Examples: 
      #| region  | browser  | usernameValue | passwordValue | pageTitle   |
      #| "scrum" | "chrome" | "admin"       | "admin123"    | "OrangeHRM" |
#
  #Scenario Outline: Verify System displays "Required" error message when username or password not provided
    #Given User navigates to application | navigateToApp <browser> <region>
    #When User does not provide username | enterValue "login.username" <usernameValue>
    #And User does not provide password | enterValue "login.password" <passwordValue>
    #And User clicks login button | clickElement "login.loginBtn"
    #Then System displays error message under username field | verifyMessage "login.usernameError" <errorMessage>
    #And System displays error message under password field | verifyMessage "login.passwordError" <errorMessage>
#
    #@scrum
    #Examples: 
      #| region  | browser  | usernameValue | passwordValue | errorMessage |
      #| "scrum" | "chrome" | ""            | ""            | "Required"   |
#
  #Scenario Outline: Verify user unable to login with invalid credentials
    #Given User navigates to application | navigateToApp <browser> <region>
    #When User enters invalid username | enterValue "login.username" <usernameValue>
    #And User enters invalid password | enterValue "login.password" <passwordValue>
    #And User clicks login button | clickElement "login.loginBtn"
    #Then System displays invalid credentials message under username field | verifyMessage "login.invalidCredentials" <errorMessage>
#
    #@scrum
    #Examples: 
      #| region  | browser  | usernameValue | passwordValue | errorMessage          |
      #| "scrum" | "chrome" | "adminXYZ"    | "admin123XYZ" | "Invalid credentials" |
