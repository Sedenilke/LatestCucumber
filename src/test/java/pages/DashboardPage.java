package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class DashboardPage extends CommonMethods {

    @FindBy(id="menu_pim_viewPimModule")
    public WebElement pimOption;

    @FindBy(id="menu_pim_viewEmployeeList")
    public WebElement empListOption;

    @FindBy(id="menu_pim_addEmployee")
    public WebElement addEmployeeOption;

    //asagidaki kod dashboard kisminda yazan butun basliklari aliyor
    @FindBy(xpath="//div[@class='menu']/ul/li")
    public List<WebElement> dashboardTabs;

    @FindBy(id="welcome")
    public WebElement welcomeMessage;

    public DashboardPage(){
        PageFactory.initElements(driver, this);
    }
}
