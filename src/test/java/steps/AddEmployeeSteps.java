package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {
    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        //after class05 - we change the below two lines of code into one
        //WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        // pimOption.click();
        //click method is coming from our commonMethods click() method which also
        //waits to the element to be clickable.
        //dashboard is coming from PageInitializer
        //pimOption is coming from the DashboardPage'den geliyor..
        click(dashboard.pimOption);
    }

    @When("user clicks on Add Employee button")
    public void user_clicks_on_add_employee_button() {
        //WebElement addEmployeeOption = driver.findElement(By.id("menu_pim_addEmployee"));
        // addEmployeeOption.click();
        click(dashboard.addEmployeeOption);
    }

    @When("user enter firstname and lastname")
    public void user_enter_firstname_and_lastname() {
        //  WebElement firstName = driver.findElement(By.id("firstName"));
        // firstName.sendKeys("soman");
        sendText(addEmployee.firstNameField, "soman");

        //WebElement lastName = driver.findElement(By.id("lastName"));
        //lastName.sendKeys("yuria");
        sendText(addEmployee.lastNameField, "yuria");
    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        // WebElement saveButton = driver.findElement(By.id("btnSave"));
        //saveButton.click();
        click(addEmployee.saveButton);
    }

    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee Added");
    }

    //for only adding one employee:
    @When("user enter {string} and {string}")
    // // otomatik  public void user_enter_and(String string, String string2) olusturdu. Ama
    //buradaki String string, String string2 does not meaningful for anybody. So We changed it
    //wit meaningful names: String firstName and String lastName. These variable names can be anything
    //but meaningful for the related method.
    //her ne kadar feature file'da direkt isim yazsam da step definition kisminda bunlari
    //parameter olarak gectigim icin burada hard coded data yapmamis oluyorum. Parameterize etmis
    //oluyorum.
    public void user_enter_and(String firstName, String lastName) {
        sendText(addEmployee.firstNameField, firstName);
        sendText(addEmployee.lastNameField, lastName);
    }

    //for multiple employee addition:
    @When("user enter {string} and {string} for adding multiple employees")
    public void user_enter_and_for_adding_multiple_employees(String firstNameValue, String lastNameValue) {
        sendText(addEmployee.firstNameField, firstNameValue);
        sendText(addEmployee.lastNameField, lastNameValue);
    }
//Asagidaki kod data table icin cikti:
//@When("user adds multiple employees and verify they are added successfully")
    //-----------asagidaki kisimda parantezin icindeki io.cucumber.datatable kismini sildik. Sadece DataTable kismi kaldi
// it means direkt import is avaiable Then we import DataTable from io.cucumber.datatable.DataTable...------------------------
//public void user_adds_multiple_employees_and_verify_they_are_added_successfully(io.cucumber.datatable.DataTable dataTable) {
    // Write code here that turns the phrase above into concrete actions
    // For automatic transformation, change DataTable to one of
    //--------------asagida oneriler var, bunlardan birini sec diyor, benim dataTable'im Maplerin listesinden
    //olusuyor:
    // (firstName, zara), (middleName, MS), (lastName, camilullah) --> First Map
    // (firstName, birgul), (middleName, MS), (lastName, ozgin) --? Second Map
    // (firstName, alina), (middleName, MS), (lastName, bob) --> Third Map
    //dolayisiyla onerilerden "List<Map<K,V>>" olani sectik.
    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
    // Double, Byte, Short, Long, BigInteger or BigDecimal.
    // For other transformations you can register a DataTableType.
    //throw new io.cucumber.java.PendingException();
//}
@When("user adds multiple employees and verify they are added successfully")
    public void user_adds_multiple_employees_and_verify_they_are_added_successfully(DataTable dataTable) throws InterruptedException {
        //get the dataTable as maps
        //dataTable yazdiktan sonra "." koydum bana oneriler sundu: asMap(), asList() etc. ben de asMap() olani sectim.
        List<Map<String, String>> employeeNames = dataTable.asMaps(); // data is coming from dataTable sol tarafi da oldugu gibi biz yazdik.
        //sirayla da Map ve Listleri import ettik.
        //getting the map from list of maps. We need one map at one time.
        for (Map<String, String> employee : employeeNames) {
            //getting the keys and
            // values from every map
            String firstNameValue = employee.get("firstName"); // firstName is the title of the data table- one of the key values.
            String middleNameValue = employee.get("middleName"); // middleName is the title of the data table- one of the key values
            String lastNameValue = employee.get("lastName"); //lastName is the title of the data table- one of the key values

            sendText(addEmployee.firstNameField, firstNameValue); //Ilk: zara
            sendText(addEmployee.lastNameField, lastNameValue); //ilk: camilullah
            sendText(addEmployee.middleNameField, middleNameValue); //ilk: MS

            //till this time one employee has been added
            //verifying the employee is HW:
            //On addEmployeePage, getting the value of employeeID:
            String newEmployeeID = addEmployee.employeeId.getAttribute("value");
            click(addEmployee.saveButton);
            //to verification, asagidaki benim yontemim:
            //Saving the employee's full name:
            String nameOfTheEmployee = firstNameValue + " " + middleNameValue + " " + lastNameValue;
            //derste:
            //List<WebElement> rowData =
            //driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));

            //---------------To verify that employee has been added successfully:------------
            click(dashboard.empListOption);
            //Sending the newEmployeeID from addEmployeePage to employeeSearchField:
            sendText(employeeList.empSearchIdField, newEmployeeID);
            click(employeeList.searchButton);

            //After searching the ID, the name that appears on the first row should match with our newly added employee name:
            String tableEmployeeFullName = employeeList.tableFirstRowFirstAndMiddleName.getText() + " " + employeeList.tableFirstRowLastName.getText();
            //Comparing the nameOfTheEmployee with tableEmployeeFullName:
            Assert.assertTrue(nameOfTheEmployee.equals(tableEmployeeFullName));
            //---------------verifying the employee is finished ---------------------
            Thread.sleep(2000); // teacher added this sleep
            click(dashboard.addEmployeeOption); //to add another employee we are still in the loop
            Thread.sleep(2000); // teacher added this sleep
        }
    }
    //Above example we saw the example of data table as list of maps, but we can also use only list, or only map while
    //with data tables.

    @When("user adds multiple employee from excel using {string} and verify it")
    public void user_adds_multiple_employee_from_excel_using_and_verify_it(String sheetName) throws InterruptedException {
//String sheetName is actually "EmployeeData"
        List<Map<String, String>> empFromExcel =
                ExcelReader.excelListIntoMap(Constants.TESTDATA_FILEPATH, sheetName);

        //it returns one map from list of maps
        Iterator<Map<String, String>> itr = empFromExcel.iterator();
        while (itr.hasNext()) {
            //it returns the key and value for employee from excel
            Map<String, String> mapNewEmp = itr.next();

            sendText(addEmployee.firstNameField, mapNewEmp.get("firstName"));
            sendText(addEmployee.middleNameField, mapNewEmp.get("middleName"));
            sendText(addEmployee.lastNameField, mapNewEmp.get("lastName"));
            sendText(addEmployee.photograph, mapNewEmp.get("photograph"));
            String empIdValue = addEmployee.employeeId.getAttribute("value");

            if (!addEmployee.checkBox.isSelected()) {
                click(addEmployee.checkBox);
            }
            sendText(addEmployee.createusernameField, mapNewEmp.get("username"));
            sendText(addEmployee.createpasswordField, mapNewEmp.get("password"));
            sendText(addEmployee.confirmpasswordField, mapNewEmp.get("confirmPassword"));
            click(addEmployee.saveButton);
            System.out.println("click taken on save button");
            //verification is in home-work
            Thread.sleep(3000);

            click(dashboard.empListOption);
            Thread.sleep(2000);
            System.out.println("click taken on emp list option");
            //to search the employee, we use emp id what we captured from attribute
            sendText(employeeList.empSearchIdField, empIdValue);
            click(employeeList.searchButton);

            //verifying the employee added from the excel file

            List<WebElement> rowData =
                    driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));

            for (int i = 0; i < rowData.size(); i++) {
                System.out.println("I am inside the loop and worried about josh");
                //getting the text of every element from here and storing it into string
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);

                String expectedData = empIdValue + " " + mapNewEmp.get("firstName")
                        + " " + mapNewEmp.get("middleName") + " " + mapNewEmp.get("lastName");

                //verifying the exact details  of the employee
                Assert.assertEquals(expectedData, rowText);

            }
            click(dashboard.addEmployeeOption);
            Thread.sleep(2000);
        }
    }
}