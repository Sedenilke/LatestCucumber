
package utils;

import org.json.JSONObject;

public class
APIPayloadConstant {
//GenerateTokenSteps bolumunde kullaniyorum
    public static String adminPayload() {
        String adminPayload = "{\n" +
                "\"email\": \"myadmin1@test.com\",\n" +
                "  \"password\": \"myadmin1234\"\n" + "}";
        return adminPayload;
    }
    public static String createEmployeePayload() {
        String createEmployeePayload = "{\n" +
                " \"emp_firstname\": \"name3\",\n" +
                "  \"emp_lastname\": \"lastName3\",\n" +
                "  \"emp_middle_name\": \"middleName3\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"1978-06-30\",\n" +
                "  \"emp_status\": \"Confirmed\",\n" +
                "  \"emp_job_title\": \"QA Engineer\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeeJsonBody() {
        JSONObject obj = new JSONObject();
        //JSONObject also holds the data as key-value pairs like maps, so
        //to add an element for it we use put method.
        obj.put("emp_firstname","name3");
        obj.put("emp_lastname","lastName3");
        obj.put("emp_middle_name","middleName3");
        obj.put("emp_gender","F");
        obj.put("emp_birthday","1978-06-30");
        obj.put("emp_status","Confirmed");
        obj.put("emp_job_title","QA Engineer");
        return obj.toString();
    }
}
