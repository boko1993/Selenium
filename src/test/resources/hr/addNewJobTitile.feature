#Feature: Add New Employee
#
#Scenario Outline: confirm Create new JobTitile is working
#Given User logs in to app | loginToApp <Browser> <region>
#When user clicks on admin tab |  clickElement "admin.userManagement"
#And user clicks on job| clickElement "admin.job"
#And user click on job titles | clickElement "job.jobTitles"
#And user click on add | clickElement "jobTitles.add"
#And user enter jobTitile | enterValue "jobTitles.title" <title>
#And user enter Job Description | enterValue "jobTitles.description" <description>
#And user enter jobTitile | enterValue "jobTitles.note" <noteValue>
#And user click on save | clickElement "jobTitles.save"
#Then user verifies succsses message | verifyMessage "jobTitles.message" <msg>
	#| Browser  | region  | title         | description | noteValue    | msg                  |
	#| "Chrome" | "scrum" | "Selenium123" | "TESTING"   | "GOOD TO GO" | "Successfully Saved" |
#
#Scenario: edit job Titile in jobs table
#Given User logs in to app | loginToApp <Browser> <region>
#When user clicks on Admin tab | clickElement "admin.userManagement"
#And user clicks on job| clickElement "admin.job"
#And user clicks on edit icon | clickElement "jobTitles.edit"
#And user enter new jobTitile | enterValue "jobTitles.title" <title>
#And user click on save | clickElement "jobTitles.save"
#| Browser  | region  | title          |
#| "Chrome" | "scrum" | "new title123" |
Feature: Add New Employee

  #Scenario: confirm Create new JobTitile is working
  #Given User logs in to app | loginToApp "Chrome" "scrum"
  #When user clicks on admin tab |  clickElement "admin.userManagement"
  #And user clicks on job| clickElement "admin.job"
  #And user click on job titles | clickElement "job.jobTitles"
  #And user click on add | clickElement "jobTitles.add"
  #And user enter jobTitile | enterValue "jobTitles.title" "itdsf101"
  #And user enter Job Description | enterValue "jobTitles.description" "TESTING"
  #And user enter jobTitile | enterValue "jobTitles.note" "GOOD TO GO "
  #And user click on save | clickElement "jobTitles.save"
  #Then user verifies succsses message | verifyMessage "jobTitles.message" "Successfully Saved"
  Scenario: edit job Titile in jobs table
    Given User logs in to app | loginToApp "Chrome" "scrum"
    When user clicks on Admin tab | clickElement "admin.userManagement"
    And user clicks on job| clickElement "admin.job"
    And user click on job titles | clickElement "job.jobTitles"
    And user clicks on edit icon | clickElement "job.edit"
    And user enter new jobTitile | enterValue "jobTitles.title" "new titile"
    And user click on save | clickElement "jobTitles.save"
