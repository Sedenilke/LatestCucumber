package APISteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.APIConstants;
import utils.APIPayloadConstant;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {
    //Asagidaki kismi APIConstant classini olusturduktan ve bu linki tum end pointlere ekledikten sonra kaldirdik.
    //cunku artik baseURI= BaseURL+END-POINT haline geri dondu
    //String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //to use my token in the project I am using static variable
    //but we did not provide any value here.
    public static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        //After creating APIConstants, we pass all of the values from that class
      /*  RequestSpecification request = given().header("Content-type","application/json")
                                     .body("{\n" +
                                        "\"email\": \"myadmin1@test.com\",\n" +
                                     "  \"password\": \"myadmin1234\"\n" + "}");*/
        RequestSpecification request = given().header(APIConstants.Header_Key_Content_Type,APIConstants.Header_Value_Content_Type)
                                        .body(APIPayloadConstant.adminPayload());
//asagidaki kodu da APIConstant classini olusturduktan sonra guncelledik.
  // Response response = request.when().post("/generateToken.php");
        Response response = request.when().post(APIConstants.GENERATE_TOKEN_URI);
   //printing the response body
   //response.prettyPrint();
   String valueOfToken = response.jsonPath().getString("token");
   token = "Bearer " + valueOfToken; //cunku biz tokeni hep " Bearer adksdfasdasd.asdasdadsa.asdadas" seklinde kullaniyoruz.
    //now we can use this token everywhere!
    }
}
