package APISteps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstant;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class APIWorkflowSteps {

    static String employee_id;
     RequestSpecification request;
      Response response;
    //Creating an employee:
    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
        //"Authorization, Content-Type, application/json, ve tum end pointleri hard coded olarak giriyorduk.
        //ama simdi Utils'in altinda APIConstant file'inda tum bu degerlerin final variablelarini olusturduk ve oradan
        //cagiracagiz:
    /*    request = given().header("Authorization",GenerateTokenSteps.token)
                                    .header("Content-Type","application/json")
                                    .body("{\n" +
                                      " \"emp_firstname\": \"name3\",\n" +
                                    "  \"emp_lastname\": \"lastName3\",\n" +
                                   "  \"emp_middle_name\": \"middleName3\",\n" +
                                    "  \"emp_gender\": \"F\",\n" +
                                   "  \"emp_birthday\": \"1978-06-30\",\n" +
                                   "  \"emp_status\": \"Confirmed\",\n" +
                                      "  \"emp_job_title\": \"QA Engineer\"\n" +
                                     "}");
    }*/
        request = given().header(APIConstants.Header_Key_Content_Type, APIConstants.Header_Value_Content_Type)
                .header(APIConstants.Header_Key_Authorization, GenerateTokenSteps.token)
                .body(APIPayloadConstant.createEmployeePayload());
    }
        @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        //ayni sekilde asagidaki end pointi de APIconstants'tan cagirdik: Burada sadece end-pointi degil end pointten
            //onceki URI'yi da birlikte getiriyorum Constant classtan. Eski durumda GenerateToken background oldugu icin
            //oradan geliyordu bu URI kismi ama tum bu constantlari APIConstanta cektigim icin artik oradan da gelmeyecegi icin
            //baseURI'dan bir yerde bahsetmem gerekiyor, bu connectioni kurmam gerekiyor. o yer de artik burasi.
            //Ayni eskisi gibi BaseURI= URL+end point oldu.
        // response = request.when().post("/createEmployee.php");

            response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
            response.prettyPrint();
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer int1) {
        response.then().assertThat().statusCode(int1); //int1 is coming from feature.file which is 201
    }

    @Then("the response body contains key {string} and value {string}")
    public void the_response_body_contains_key_and_value(String messageKey, String messageValue) {
        //burada body(key, equalTo(value)) aslinda key'in valuesunu value ile karsilastiriyor.
       response.then().assertThat().body(messageKey,equalTo(messageValue));
    }

    @Then("the employee id {string} is stored as global tobe used for other request")
    public void the_employee_id_is_stored_as_global_tobe_used_for_other_request(String emp_id) {
        //asagidaki kisim employee_idyi cagiriyor ve bunu global olarak kayit etmem lazim. yani bunu class
        //levelinda save etmem lazim. onun icin ust kisma "static String employee_id" variableini olusturuyorum.
       employee_id = response.jsonPath().getString(emp_id);
        System.out.println(employee_id);

    }
    //-------------------------------------------------------------------------------------------------------
//retriving the newly created employee
    @Given("a request is prepared for getting a created employee")
    public void a_request_is_prepared_for_getting_a_created_employee() {
        request = given().header(APIConstants.Header_Key_Authorization,GenerateTokenSteps.token)
                .header(APIConstants.Header_Key_Content_Type, APIConstants.Header_Value_Content_Type)
                .queryParam("employee_id",employee_id);
        //burada "emploee_id"yi bir seferlik kullandigimiz icin hardcode kalabilir dedi.
    }

    @When("a GET call is made to get this employee")
    public void a_get_call_is_made_to_get_this_employee() {
        response = request.when().get(APIConstants.GET_EMPLOYEE_URI);
    }

    @Then("the status code for this employee is {int}")
    public void the_status_code_for_this_employee_is(Integer int1) {
        response.then().assertThat().statusCode(int1);
    }

    @Then("the employee id {string} should match with global employee_id")
    //String string kismindaki string aslinda employee.employee_id
    public void the_employee_id_should_match_with_global_employee_id(String string) {
        String temp_emp_id = response.jsonPath().getString(string);
        //burada artik regular assert kullanabilirim. Response'dan gelen employee_id ile global employee_id'yi
        //karsilastiriyorum.
        Assert.assertEquals(temp_emp_id,employee_id);
    }

    @Then("the retrieved data at {string} object should match with the data used for creating the employee")
    //Asagidaki kisimda yer alan String employeeObject is employee object which holds the complete value from the response
    //data table provides the data in the form of key and value pair
    public void the_retrieved_data_at_object_should_match_with_the_data_used_for_creating_the_employee(String employeeObject, DataTable dataTable) {
        List<Map<String,String>> expectedData = dataTable.asMaps();
        Map<String, String> actualData = response.body().jsonPath().get(employeeObject);
        for(Map<String, String> map: expectedData) {
            //I need only keys from the return map
            Set<String> keys = map.keySet();
            for(String key:keys) {
               String expectedValue = map.get(key);
               String actualValeu = actualData.get(key);
               Assert.assertEquals(expectedValue,actualValeu);
            }
        }
    }
    //-------------------------------------------------------------------
    //Create an employee using json
    @Given("a request is prepared for creating an employee by passing json body")
    public void a_request_is_prepared_for_creating_an_employee_by_passing_json_body() {
        request = given().header(APIConstants.Header_Key_Content_Type, APIConstants.Header_Value_Content_Type)
                .header(APIConstants.Header_Key_Authorization, GenerateTokenSteps.token)
                .body(APIPayloadConstant.createEmployeeJsonBody());
    }
}
