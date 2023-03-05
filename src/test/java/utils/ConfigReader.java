package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;

    public static Properties readProperties(String filePath) {

        try {
            //We are locating the file and find the document:
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            //java readable filei ekledi:
            prop.load(fis);
            //eger filei bulamazsa bunu FileInputStream fis = new FileInputStream(filePath) kismi icin olusturdu.
        } catch (FileNotFoundException e)  {
            e.printStackTrace();
            //file'i buldu ama okuyamiyorsa bunu da prop.load(fis) kismi icin olusturdu:
        } catch (IOException e) {
          e.printStackTrace();
        }
        return prop;
    }

    public static String getPropertyValue(String key) {
        //getProperty is the method which will read the value as per
        //the key provided
      return  prop.getProperty(key);
    }
}

