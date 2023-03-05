package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

//asagidaki constructor adiminda "driver" kullandigim icin CommonMethods'u extend ettim burada.

public class LoginPage extends CommonMethods {
//this syntax is coming from PageFactory, eskiden seleniumda baska turlu buluyordum elemanlari:
// burada artik syntax degisiyor:.
    @FindBy(xpath ="//input[@id='txtUsername']")
    public WebElement usernameTextField;

    @FindBy(id="txtPassword")
    public WebElement passwordTextField;

    @FindBy(xpath="//*[@id='btnLogin']")
    public WebElement loginButton;

    @FindBy(xpath="//*[@id='spanMessage']")
    public WebElement errorMessage;

    public LoginPage(){
        //bu bildigim constructor, fakat initialize etmek istedigim objeler Java type degil.Selenium type
        //O yuzden burada selenium page factory initialize methodunu kullanmak zorundayim objeleri initialize etmek icin.
        //call selenium page factory initElements --> initialize the elements
        PageFactory.initElements(driver, this);
    }

}
