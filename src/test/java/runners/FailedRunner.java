package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//this runner class is only responsible to run failed scenarios
    @RunWith(Cucumber.class)
    @CucumberOptions(
            //bunun pathi fix: her zaman asagidaki path olmali
            features = "@target/failed.txt",
            glue = "steps",
            //we do not need dryRun here:
            // dryRun = false,
            //we do not need tags here:
            // tags = "@tc1101",
            //to remove irrelavant information from console, you need to set monochrome to true
            monochrome = true,
            //pretty keywords prints the steps in the console to increase readability
            //Burasi FailedRunner class oldugu icin tum raporlama kisimlarini buradan cikarttik.Sadece
            //ilk denemede belki server, belki degerler yuzunden fail eden senaryoya bir hak daha vermek istedik.
            plugin = {"pretty"}
    )
    public class FailedRunner {
}
