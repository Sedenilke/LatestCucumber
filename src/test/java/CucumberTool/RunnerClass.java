package CucumberTool;

import io.cucumber.junit.Cucumber; //imports @RunWith annotation
import io.cucumber.junit.CucumberOptions; //imports the @CucumberOptions
import org.junit.runner.RunWith;

// @RunWith annotation tells JUnit that tests should run using Cucumber class present in 'Cucumber.api.junit' package
@RunWith(Cucumber.class)
//@CucumberOptions This annotation tells Cucumber a lot of things like where to look for feature files,
// what reporting system to use and some other things also.
// But as of now in the above test,
// we have just told it for the Feature file folder.
@CucumberOptions (
        //asagidaki kod login.feature dokumanin ustune sag tikladim ve oradaki pathi kopyaladim
        //daha kisasini da yazabilirim: src'dan baslayarak :src\test\java\CucumberTool\Login.feature
        features = "C:\\Users\\ilkes\\IdeaProjects\\CucumberBatch14\\src\\test\\java\\CucumberTool\\Login.feature"

)
public class RunnerClass {
}
