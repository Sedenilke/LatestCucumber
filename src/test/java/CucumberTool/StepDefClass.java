
package CucumberTool;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class StepDefClass {
    WebDriver driver;
    @Given("user is navigate to HRMS application")
    public void user_is_navigate_to_hrms_application() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {
        WebElement userNameField = driver.findElement(By.cssSelector("input#txtUsername"));
        userNameField.sendKeys("Admin");
        WebElement passwordField = driver.findElement(By.cssSelector("input#txtPassword"));
        passwordField.sendKeys("Hum@nhrm123");
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        WebElement loginBttn = driver.findElement(By.cssSelector("input#btnLogin"));
        loginBttn.click();
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {

        WebElement welcomeMessage = driver.findElement(By.cssSelector("a#welcome"));
        if(welcomeMessage.isDisplayed()) {
            System.out.println("You successfully logged in");
        }
        else {
            System.out.println("You could not logged in");
        }
    }
}
