package utils;

public class Constants {
    //public static final String CONFIGURATION_FILEPATH = "C:\\Users\\ilkes\\IdeaProjects\\CucumberBatch14\\src\\test\\resources\\config\\config.properties";
    //C:\Users\ilkes\IdeaProjects\CucumberBatch14\src\test\resources\config\config.properties--> bu kisim benim makinama ait,
    //fakat "src\test\resources\config\config.properties" kisim herkesin makinasinda ayni, bastaki kismi bir sekilde alabilirsem
    // ki bu su methoddan geliyor: System.getProperty("user.dir") daha sonraki kismi String olarak ekleyebilirim.
    //ama eklerken de slashlarin yonunu degistirmem gerekiyor.

    //user.dir is actually user.directory
    public static final String CONFIGURATION_FILEPATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";

    //implicit wait
    public static final int IMPLICIT_WAIT =10;

    //explicit wait:
    public static final int EXPLICIT_WAIT =20;

    public static final String TESTDATA_FILEPATH =
            System.getProperty("user.dir") + "/src/test/resources/testdata/batch14excel.xlsx";
//burada aslinda ben fiziksel olarak screenshot klasorunu olusturmadim. Common method kisminda screenshot metodunu yazip runner
//classta calistirdiktan sonra program kendi olusturdu bunu.
public static final String SCREENSHOT_FILEPATH = System.getProperty("user.dir") + "/screenshots/";
    }