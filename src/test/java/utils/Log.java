package utils;

import org.apache.log4j.*;
//bu classin amaci, kodun nerede takildigini anlamak icin
//odun aralarina system.out.println("login sayfasndayim") gibi notlar koyuyordum, bu kisimlar
// consolede gorunuyordu ama cok zaman aliyor bunlari yazmasi da ama testi run ettigim zaman
//o kadar yazinin icinde bu notlar kayboluyordu. Bu ayri bir yerde bu notlari gormemi sagliyor.
public class Log {

    //initialize log4j logs
    public static Logger log = Logger.getLogger(Log.class.getName());

    //understand the scope:
    //if my test case start
    //if my test case end
    //if any message I need in between


    public static void startTestCase(String testCaseName) {
        //log.info is similar to System.out.println. system.out.println is the one that we
        //use to write something in the console. log.info is the same functionality that writes on log file.
        log.info("*********************");
        log.info("**************************************");
        log.info("######################        " + testCaseName + "         ######################");
        log.info("*********************");
        log.info("**************************************");
    }

    public static void endTestCase(String testCaseName) {
        log.info("######################");
        log.info("##################################");
        log.info("######################        " + testCaseName + "         ######################");
        log.info("######################");
        log.info("##################################");
    }

    //to print some text in between my code
    //buraya n number of methods yazabiliriz. ihtiyacimiza gore
    //scope of unlimited here.
    public static void info(String message) {
        log.info(message);
    }

    public static void warning(String warning) {
        log.info(warning);
    }
}
