Feature: Dashboard functionality
  Scenario: Verify Dashboard
    #asagidakilerin hepsi step decleration. Interfaceteki public void method(); kismi gibi
    #daha sonra bunu definition yapmam gerekiyor. Bunu da java kisminda steplerde yapiyorum.
    #Yani buradaki her bir satir bir tanimladigim bir methoda donusuyor.ilgili adimla iliskili
    #methoda nasil gidecegim?  ilgili adimin uzerine sag tikla ve go to --> declaration or usage sec.
    Given user is navigate to HRMS application
    When user enters valid username and valid password
    And user clicks on login button
    Then user is successfully logged in
    #difference between verify and validate:
    #verify means to define the WebElement, validate means that compare the ingredients like specific text.
    Then user verify dashboard page
