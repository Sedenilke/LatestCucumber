package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
       /* asagidaki uzun path yerine soyle yazdik:
        features = "C:\\Users\\ilkes\\IdeaProjects\\CucumberBatch14\\src\\test\\java\\CucumberTool\\Login.feature"
        features we use to provide the path of all the feature files daha onceki derste specific bir feature
        dokumanini isaret etmistik.(login.feature)
        asagidaki features features klasorune nasil gidecegimi gosteren yol, bu herkesin bilgisayarinda ayni dedi*/
        /*value = When I add a new scenario, to get its own defined steps I can add the name of the folder like this:
        "src/test/resources/features/login.feature" it solves my problem, but here I do something opposite to framework structure, I do
            not want any part of the framework is dependanble. so I need to find some other solution.I found the solution with tags and DryRun*/
        features = "src/test/resources/features/",
        /*sadece feature kismini yazinca hata verdi, ama mesela Login.feature dokumanini direkt
        calistirinca calisti ama runner class feature dokumanlari ilgili steplerle birlestiremiyor.
        onun icin asagidaki glue = "steps" kismini ekledim. Zaten daha onceden features-steps eslemesi
        yaptigim icin ilgili featurelarla gluelari bir araya getirdik. Burada steps klasoru icin daha uzun bir path yazmadim
        bunun sebebi sanirim runner packageinin steps packagei ile ayni yerde olmasi*/
        glue = "steps",
       /* Class03 - till now we created new feature files and to get step definitions we run all the feature classes here.
        but it is pretty time-consuming, because it runs all the feature files whether they have unimplemented step definitions or not.
        Maybe I just add a new scenario only one statement is new. To reach this default method, I have to wait
        the program to run all the feature files.
        To solve this problem we have dryRun Cucumber optin, it is false in default. But when we change it to true
        what it does is it scans really quickly the gherkin steps and execute the unimpletement steps on the console
        without actually executing the code.
        When we set dry run to true, it stops actual execution
        it will quickly scan all the gherkin steps whether they are implemented or not.
        So we can get the missing steps here to create Steps class.
        when we set it false, it starts execution again */
        dryRun = false,
       /* manager asks what did you do in sprint3 and sprint1 we use the tags that we added to the
        feature documents. If manager asks what dod you do in Sprint1 and Spring3? My tag should be "@Sprint1 or @Sprint3"
        NOT "AND" Because "And" means I need to bring the common scenarios, but I want to execute/bring the
        all scenarios either in Sprint1 or Sprint3" So we use "or" We use "or" between these 2 tags to bring them together for and. All test cases are
        the part of regression test, one scenario can have multiple tags. They will have TCNumbers, which sprint they
        have been created we need to keep track of this information, and each test case is a part of regression test.*/
        tags = "@datatable",
       /* to remove irrelevant information from console, you need to set monochrome to true. In our examples
        We did not see very irrevalent writings in the console, but teacher said we might see a lot of meaningles numbers.
        Monochrome bu yazimlari engelliyor.*/
        monochrome = true,
       /* pretty keywords prints the steps in console to increase readability.
        asagidaki bolumleri bu kisim getiriyor. Gherken language olarak stepi goruyorum ve onun hangi pakette kodunun yer
        aldigini da hemen yaninda goruyorum.
        When user enters valid username and valid password # steps.LoginSteps.user_enters_valid_username_and_valid_password()
        And user clicks on login button                    # steps.LoginSteps.user_clicks_on_login_button()
        You successfully logged in
        Then user is successfully logged in                # steps.LoginSteps.user_is_successfully_logged_in()*/
        //to generate the reports we need plugin of runner class
        //When we generate any report, this should be under target folder.
        //html:target/cucumber.html --> I want to generate html type of reports and I
        // want them under the folder target in cucumber.html
        //to open the html type of reports we need to open it using chrome: right click --> open in --> browser --> chrome
        //Burada anladigim kadariyla her zaman en son raporu goruyorum.
        // Yani yeni raporlar surekli eskisinin uzerine yaziliyor ve Hook kismindaki @After'a screenshot methodunu eklemeden
        //once bu rapor screenshoti gostermiyordu.
        //Bu rapor iyi guzel ama ben daha iyisini istiyorum dedi hoca.
        //Bu arada testi runner classtan calistirmazsam, bu raporu elde edemiyorum. Cunku "plugin" runner classta.
        //if we want json(java script object notation) type of reports.
        //report is not generated if we run the test using feature file, because plugin option is only available in runner class.

       //Class08, we run 100 tests cases and some of them failed, it maybe server error, so maybe
        //next time they will pass. to retest this scenarios we add another plugin called: rerun. Bu dokuman failed.txt is coming creating under target folder.
        //this txt document holds all the scenarios which are failed during execution
        //herhangi bir senaryo bir adimda fail ederse bu adimi gosterecek sekilde bu dokumana isleniyor:
        //Ornegin: file:src/test/resources/features/Login.feature:4, sadece bu bir senaryoyu tekrar run etmek istiyorum.
        //bunun ici

        plugin = {"pretty" , "html:target/cucumber.html", "json:target/cucumber.json" , "rerun:target/failed.txt"}
)

public class SmokeRunner {
}
