Feature: Login Page

  Scenario: verify logOut option is presant
    Given User navigates to application login page | loginToApp "Chrome" "scrum"
    When user clicks on account icon | clickElement "home.userLog"
    Then user verify logOut is visible | elementDisplayed "home.logOut"
    When user clicks logout | clickElement "home.logOut"
    Then user verefies lands on login page | verifyPageTitle "OrangeHRM"

    
    
    Scenario: 