Feature: Add Employee
Background:
  When user enters valid username and valid password
  And user clicks on login button
  Then user is successfully logged in
  When user clicks on PIM option
  And user clicks on Add Employee button

  @sprint3 @regression
  Scenario: Adding one employee
    #Given user is navigated to HRMS application
    #When user enters valid username and valid password
    #And user clicks on login button
    #Then user is successfully logged in
    #When user clicks on PIM option
    #And user clicks on Add Employee button
    And user enter firstname and lastname
    And user clicks on save button
    Then employee added successfully

@TestThisOne
    Scenario: Adding one employee using feature file
       #Given user is navigated to HRMS application
      #When user enters valid username and valid password
      #And user clicks on login button
      #Then user is successfully logged in
      #When user clicks on PIM option
      #And user clicks on Add Employee button
      And user enter "zalam" and "alia"
      And user clicks on save button
      Then employee added successfully

  #When we want to add multiple employees we use Scenario Outline instead of Scenario
  #here we are trying to reach data driven testing same thing as dataprovider in TestNG
  #In the beginning Scenario Outline gives error, sag tikladik ve create example sample sectik
  #asagidaki example bolumunu ondan sonra acti.Daha sonra isim ve soyisimleri bu bolumlerin altina
  #tek tek ekledik.
  #Burada scenario yazmiyoruz, scenario outline yaziyoruz.
  @outline
  Scenario Outline: Adding multiple employees using feature file
    And user enter "<firstName>" and "<lastName>" for adding multiple employees
    And user clicks on save button
    Then employee added successfully
    Examples:
      | firstName | lastName |
      | gulham    | mazar    |
      | rampal    | chambel  |
      | azam      | asel     |

    #asagidaki When adimini ikiye bolmedik burada.
    @datatable
  Scenario: Adding multiple employees using data table
    When user adds multiple employees and verify they are added successfully
      |firstName|middleName|lastName|
      |zara    |MS        |camilullah|
      |birgul  |MS        |ozgin     |
      |alina   |MS        |bob       |
# "EmployeeData" is the name of the sheet in the excel document
  @excel @TestThis
  Scenario: Adding multiple employees using excel file
    When user adds multiple employee from excel using "EmployeeData" and verify it
