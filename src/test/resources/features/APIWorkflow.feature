Feature: API Workflow Test
  #for every type of operation:CRUD we need token, so
  #we need to keep "Given a JWT generated" adimini backgrounda almaliyim.
  Background:for generating the token before every request
    Given a JWT is generated
  @API
  Scenario: API test case for creating the employee
    #Given a JWT is generated
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the response body contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global tobe used for other request
  @API
    Scenario: Getting the created employee
      Given a request is prepared for getting a created employee
      When a GET call is made to get this employee
      Then the status code for this employee is 200
      And the employee id "employee.employee_id" should match with global employee_id
      And the retrieved data at "employee" object should match with the data used for creating the employee
      |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
      |name3        |lastName3   |middleName3    |Female    |1978-06-30  |confirmed |QA Engineer|

      #yukaridaki degerlerin hepsini APIPayloadConstant classindan aldim. oradaki create employee kismindan
    #ama hatirlarsak emp_gender kismini "F" gonderip "Female" ciktisi almistik, ya da "M" gonderip "Male" almistik.
    #onun icin burada da "F" olan degeri "Female" olarak degistirdik.

  @API
  Scenario: API test case for creating the employee using json body
    #Given a JWT is generated
    Given a request is prepared for creating an employee by passing json body
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the response body contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global tobe used for other request