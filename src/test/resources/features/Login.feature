
Feature: Login functionality

  @sprint3 @regression @tc1101
  Scenario: Valid admin login
    #asagidaki given adimini @Before a aldigim icin artik ilk adimi kullanmama gerek yok
    #onun icin SILMIYORUM ama commente aliyorum bunu Comment yapmazsam, iki kere browser aciyor, biri
    # stepten geliyor, digeri de hooks'taki beforedan. Birinde
    #ilerliyor ve testi tamamliyor ama digerinde browser acik kaliyor ve oyle duruyor.
    #Given user is navigated to HRMS application
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in

  @regression @tc1102
  Scenario: Valid ess login
    #Given user is navigated to HRMS application
    When user enters ess username and ess password
    And user clicks on login button
    Then user is successfully logged in

  @tc1103
  Scenario: Invalid admin login
    #Given user is navigated to HRMS application
    When user enters invalid username and password
    And user clicks on login button
    Then error message displayed

  @tc1105
  Scenario Outline: Invalid login functionality
    When user enters different "<username>" and "<password>" and verify the "<error>" for it
    Examples:
      | username | password | error |
      |admin     |cristiano |Invalid credentials|
      |ronaldo   |Hum@nhrm123|Invalid credentials|
      |          |Hum@nhrm123|Username cannot be empty|
      |admin     |           |Password cannot be empty|
