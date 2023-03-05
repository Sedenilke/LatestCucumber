package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonMethods;
import utils.ConfigReader;

import java.util.List;

public class EmployeeSearchSteps extends CommonMethods {
//DAY04 -AfterCreating more Common Methods
@When("user clicks on EmployeeList option")
public void user_clicks_on_employee_list_option() {
    //WebElement empListOption = driver.findElement(By.id("menu_pim_viewEmployeeList"));
    //click(empListOption);
    click(dashboard.empListOption);
    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}
 @When("user enter valid employee id")
    public void user_enter_valid_employee_id() {
        //WebElement empIdField = driver.findElement(By.id("empsearch_id"));
        //sendText(empIdField, "45154A");
        // sendText(empIdField, ConfigReader.getPropertyValue("empId"));
        //After Class05:
        sendText(employeeList.empSearchIdField, "45154A");
    }


    @Then("user see employee information is displayed")
    public void user_see_employee_information_is_displayed() {

    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
    sendText(employeeList.searchNameField, "AA");

    }
    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
    jsClick(employeeList.searchButton);
    }
    @Then("user validates the employee name on the list")
    public void user_validates_the_employee_name_on_the_list() {
        String nameOfTheEmployee = employeeList.searchNameField.getText();
        String tableEmployeeFullName = employeeList.tableFirstRowFirstAndMiddleName.getText() + " " + employeeList.tableFirstRowLastName.getText();
        //Comparing the nameOfTheEmployee with tableEmployeeFullName:
        Assert.assertTrue(tableEmployeeFullName.contains(nameOfTheEmployee));
    }
}
