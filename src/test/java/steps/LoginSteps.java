package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;


import java.util.concurrent.TimeUnit;


//dersin ilerleyen kisminda CommonMethods classini olusturduk ve orada
//openBrowserAndLaunchApplication methodunu olusturduktan sonra buraya o classi extend ettik
//orada da driver tanimladigimiz icin buradaki WebDriver driver' i da kullanmamiza gerek kalmadi.

public class LoginSteps extends CommonMethods {
    //WebDriver driver;
    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
    /*    WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);*/
        openBrowserAndLaunchApplication();
    }

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() {
       // WebElement userNameField = driver.findElement(By.cssSelector("input#txtUsername"));
        //userNameField.sendKeys("Admin"); bunlar hep hardcoded bunlari da config.propertiesden cekmem gerekiyor
       // userNameField.sendKeys(ConfigReader.getPropertyValue("username"));
        sendText(login.usernameTextField, ConfigReader.getPropertyValue("username"));
       // WebElement passwordField = driver.findElement(By.cssSelector("input#txtPassword"));
      //  passwordField.sendKeys("Hum@nhrm123");
       // passwordField.sendKeys(ConfigReader.getPropertyValue("password"));
        sendText(login.passwordTextField, ConfigReader.getPropertyValue("password"));
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //WebElement loginBttn = driver.findElement(By.cssSelector("input#btnLogin"));
        //loginBttn.click();
        click(login.loginButton);
    }

    @Then("user is successfully logged in")
    public void user_is_successfully_logged_in() {
       // WebElement welcomeMessage = driver.findElement(By.cssSelector("a#welcome"));
        //if(welcomeMessage.isDisplayed()) {
        //after class05
        if (dashboard.welcomeMessage.isDisplayed()) {
            System.out.println("You successfully logged in");
        }
        else {
            System.out.println("You could not logged in");
        }
    }
    @When("user enters ess username and ess password")
    public void user_enters_ess_username_and_ess_password() {
        //asagidaki selenium kodun yerine POM'da olusturdugum LoginPage'den userNameFieldi LoginPage'in oBjesini olusturup cagirabilirim'
       // WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //sendText(usernameField, "asmahuma321");
        // LoginPage login = new LoginPage();
        // WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //asagidaki kullanici adini ve paraloyi sistemde olusturduk. Add new employee dedik, bilgilerini
        //girdikten sonra kullanici adi ve sifresi olusturduk ve save dedik. Sonra sayfadan logout olup
        //bu kullanici adi ve sifre ile sisteme girip giremedigimizi test ettik.
        sendText(login.usernameTextField, "asmahuma321");
        //   WebElement passwordField = driver.findElement(By.id("txtPassword"));
        sendText(login.passwordTextField, "Hum@nhrm123");
    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_username_and_password() {
        //LoginPage login = new LoginPage();
        // WebElement usernameField = driver.findElement(By.id("txtUsername"));
        sendText(login.usernameTextField, "admin123");
        //  WebElement passwordField = driver.findElement(By.id("txtPassword"));
        sendText(login.passwordTextField, "Hum@nhrm");
    }
    @Then("error message displayed")
    public void error_message_displayed() {
        System.out.println("Error message displayed");
    }


    @When("user enters different {string} and {string} and verify the {string} for it")
    public void user_enters_different_and_and_verify_the_for_it(String userName, String password, String errorMessage) {
    sendText(login.usernameTextField, userName);
    sendText(login.passwordTextField, password);
    click(login.loginButton);


    String actualErrorMessage = login.errorMessage.getText();
    //Asagidaki assert Junitten geliyor. TestNG'den degil. O yuzden soft assertion/ hard assertion diye birsey yok.
        Assert.assertEquals(errorMessage, actualErrorMessage);
    }
}