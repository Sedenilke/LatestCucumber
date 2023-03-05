package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.DOMConfiguration;
import steps.PageInitializer;

import javax.xml.transform.dom.DOMLocator;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends PageInitializer {
    public static WebDriver driver;

    public static void openBrowserAndLaunchApplication() {
        ConfigReader.readProperties(Constants.CONFIGURATION_FILEPATH);

        switch(ConfigReader.getPropertyValue("browser")) {
            case "chrome":
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default: // if user write something else for the browser name mesela firefom
                throw new RuntimeException("Invalid Browser name");
        }
        driver.manage().window().maximize();
        //asagidaki methodu, 4. dersten sonra olusturdugumuz constant emplicit wait
        //paramaterinden sonra soyle degistirdik:
        //driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(ConfigReader.getPropertyValue("url"));
        //Class05
        //ne yaparsak yapalim her zaman bu methodu kullaniyoruz.
        //o yuzden bu methodun icinde pageInitializer classindan initializePageObjects() methodunu cagiriyoruz.
        //heryeni sayfa olusturdugumda altina bununla ilgili objeyi ekliyor olacagim.
        initializePageObject();
        //To configure the file and pattern of it, we need to call the file
        //DOMConfigurator is a class in log4j.xml
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("My first test case is Login Test");
        Log.info("My login test is going on");
        Log.warning("My test case might be failed");
    }
    //CLASS04:
    //close the browser
    public static void closeBrowser() {
        Log.info("My test case is about to complete");
        Log.endTestCase("This is my login test again");
        driver.quit();
    }
    //generalize the sendKeys method-Class04
    public static void sendText(WebElement element, String textToSend){
        element.clear();
        element.sendKeys(textToSend);
    }
    //to get WebDriver wait -Class04
    public static WebDriverWait getWait() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.EXPLICIT_WAIT);
        return wait;
    }

    //this method will wait for the element to be clickable - Class04
    public static void waitForClickability(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    //Class04
    public static void click(WebElement element) {
        waitForClickability(element);
        element.click();
}
    //bazi zamanlarda seleniumun click methodu ise yaramiyor dedi, bunun icin javascriptin clickini kullanmaliyiz
    //dedi bunun icin asagidaki methodlari olusturduk.
    //this method will return JavascriptExecutor object -Class04
    public static JavascriptExecutor getJSExecutor(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js;
    }

    //this method will perform click on element using javascript executor -Class04
    public static void jsClick(WebElement element){
        getJSExecutor().executeScript("arguments[0].click();", element);
    }

    //selecting the dropdown using text -Class04
    public static void selectDropdown(WebElement element, String text) {
        Select s = new Select(element) ;
        s.selectByVisibleText(text);
    }


    public static byte[] takeScreenshot(String fileName){
        //Indicates a driver or an HTML element that can capture a screenshot and store it in different ways.
        //Convert web driver object to TakeScreenshot:
        TakesScreenshot ts = (TakesScreenshot) driver;
        //cucumber takescreenshot as an array of bytes:
        byte[] picBytes = ts.getScreenshotAs(OutputType.BYTES);
        //Call getScreenshotAs method to create image file
        File sourceFile =  ts.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(Constants.SCREENSHOT_FILEPATH + fileName + " " +
                getTimeStamp("yyyy-MM-dd-HH-mm-ss")+".png");

        try {
            //Copy file at destination
            FileUtils.copyFile(sourceFile,destinationFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return picBytes;
    }
    public static String getTimeStamp(String pattern){
        Date date = new Date(); // bu aslinda simdiki zamani donuyor. yani sout(date) desek current date'i verecek.
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        //format method is used to format a given date into Date/Time string.
        // Basically the method is used to convert this date and time into a particular format for say mm/dd/yyyy.
        return sdf.format(date); // bunun tarihini sdf'e verdigim pattern seklinde formatla
    }
}

