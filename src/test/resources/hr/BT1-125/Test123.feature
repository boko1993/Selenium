Feature: header of webpage

  Scenario Outline: Verify top bar section and title is displayed on each page
    Given User goes to url | loginToApp <browser> <region>
    Then User verifies topbar is visible | elementDisplayed "header.body"
    And User verifies dash text | verifyMessage "header.title" "Dashboard"
    When User switches to another menu page
      | objectKey        |
      | menu.admin       |
      | menu.pim         |
      | menu.leave       |
      | menu.time        |
      | menu.recruitment |
      | menu.myinfo      |
      | menu.performance |
      | menu.directory   |
      | menu.buzz        |

    Then user verifies that following elements texts are visible
    | objectKey        | expMessage  |
    | menu.admin       | Admin       |
    | menu.pim         | PIM         |
    | menu.leave       | Leave       |
    | menu.time        | Time        |
    | menu.recruitment | Recruitment |
    | menu.myinfo      | My Info     |
    | menu.performance | Performance |
    | menu.directory   | Directory   |
    | menu.buzz        | Buzz        |
    
    Examples: 
      | browser  | region  |
      | "chrome" | "scrum" |

  Scenario Outline: Verify top bar section and title is displayed on each page
    Given User goes to url | loginToApp <browser> <region>
    And User verifies header is displayed on homepage | elementDisplayed "header.title"
    When User switches to another menu page
      | objectKey        | 
      | menu.admin       |
      | menu.pim         |
      | menu.leave       |
      | menu.time        |
      | menu.recruitment |
      | menu.myinfo      |
      | menu.performance |
      | menu.directory   |
      | menu.buzz        |
    Then User verifies header is displayed on each page | elementDisplayed "header.title"



    Examples: 
      | browser  | region  |
      | "chrome" | "scrum" |
