Feature: US-321 Searching the employee
# Background is related to the steps inside the same feature file. That we use
  # for common steps till we run into a different step. and before every test case my background will execute.
  #iki senaryo employee'i id ile aratma veya isim ile aratmaya kadar ayni geliyor. bir sonraki adimda farklilasiyor
  #ondan  sonraki iki adim yine ayni ama kurala gore bir kere adimlarin birligi bozulduktan sonra diger adimlar ayni olsa bile
  #onlari backgrounda alamiyorum.

  Background:
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in
    When user clicks on PIM option
    And user clicks on EmployeeList option

  @batch14 @sprint4
  Scenario: Search employee by id
    #Given part will come from Hooks class
    #Given user is navigated to HRMS application
    #Asagidaki 5 step will come from Background
    #When user enters valid username and valid password
    #And user clicks on login button
    #Then user is successfully logged in
    #When user clicks on PIM option
      # And user clicks on EmployeeList option
    When user enter valid employee id
    And user clicks on search button
    Then user see employee information is displayed

  @sprint4 @test @Test111
  Scenario: Search employee by name
    #Given user is navigated to HRMS application
    #When user enters valid username and valid password
    #And user clicks on login button
    #Then user is successfully logged in
    #When user clicks on PIM option
    #And user clicks on EmployeeList option
    When user enters valid employee name
    And user clicks on search button
    Then user validates the employee name on the list