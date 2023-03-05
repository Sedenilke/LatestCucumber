package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {
    //There are 2 Before options org.junit and io.cucumber.java
    //we need to use the io.cucumber.java
    @Before
    public void preCondition() {
        openBrowserAndLaunchApplication();
    }

    //here we use special class called scenario class from cucumber
    //this class holds the complete information of your execution:
    // name of the scenario, how much time it took to execute the test case,
    //what is the status of your test case,every single information will be holding by our scenario class.
    //to get the results we need scenario class.
    //this is why we add Scenario scenario kismini parantezin icine ekledik.
    @After
    public void postCondition(Scenario scenario) {

        byte [] pic;
        if(scenario.isFailed()) {
            //failed screenshot will be available inside failed folder
            //scenario.getName() give us the name of the scenario
            pic = takeScreenshot("failed/" + scenario.getName());
        }
        else {
            pic = takeScreenshot("passed/" + scenario.getName());
        }
        //to attach the screenshot in our report
        //attach method is asking 3 parameters: array of byte, third is scenario name. Bu kismi ekleyene kadar
        //cucumber html reportta screenshot gorunmuyordu.
        scenario.attach(pic, "image/png", scenario.getName());

        closeBrowser();
    }
}
