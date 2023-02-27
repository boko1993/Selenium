Feature: Testing the Register Page

  Scenario Outline: Register Page
    Given User navigate to Register Page | navigateToApp <Browser> <Region>
    When User select the Male Gender | clickElement "register.gender.male"
    And User enters FirstName | enterValue "register.firstName" <FirstName>
    And User enters LastName | enterValue "register.lastName" <LastName>
    And User select the May of birth | selectDropDown "register.date.day" "1" "index"
    And User select the Month of birth | selectDropDown "register.date.month" "1" "index"
    And User select the Month of birth | selectDropDown "register.date.year" "1993" "text"
    And User enters FirstName | enterRandomEmail "register.email"
    And User enters CompanyName | enterValue "register.companyName" <Companyname>
    And User clicks on newsletter option | clickElement "register.newsletter"
    And User enters Password | enterValue "register.password" "123456789"
    And User confirms Password | enterValue "register.confirmPassword" "123456789"
    And User clicks on Register button | clickElement "register.registerBtn"
    Then User verifies success Message | verifyMessage "register.successMessage" "Your registration completed"

    Examples: 
      | Browser | Region  | FirstName | LastName | Companyname |
      | "chrome"  | "scrum" | "Bokan"   | "Cena"   | "NoorTeck"  |
