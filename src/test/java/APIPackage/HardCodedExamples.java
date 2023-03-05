package APIPackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.equalTo;
//tum seyleri kapsamak icin CoreMatchers.equalTo yerine manual olarak asagidaki classi import ettik:
import static org.hamcrest.Matchers.*;
//once getOneEmployee, daha sonra da createAnEmployee methodlarini olusturduk, bu classi oldugu gibi
//run etmek istedigimizde hata verdi, cunku siralama top to bottom seklindeydi ve employee olusturmadan
//oradan gelecek olan employee_id'yi kullanmaya calistik. Onun icin basarili olmadi.
//burada TestNG kullanmadigimiz icin priority ozelligini de kullanamiyoruz. Onun yerine @FixMethodOrder
//annotationini kullanacagiz, BU Junitten geliyor.Bunu da classin en basina yazacagiz.
//daha sonra da parametre olarak MethodSorters.NAME_ASCENDING sectik ve methodlarin adini buna gore guncelledik.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {
    static String employee_id;

    //one thing to remember:
    // postmande URI icin Base URL ve end pointi bir araya getiriyorduk.ve methodun yanina
    //bu sekilde yaziyorduk: POST {{QABaseURL}}{{endpoint}} ama burada Given-- prepare the request
    //ve send the request/hitting the end point which is When() ayri adimdalar, dolayisiyla bunlari ayri
    //ayri ele almaliyim.burasi icin bizim baseURI'limizi sadece URL olusturuyor. end pointi bunun
    //disinda tuttuk simdilik.

    //asagidaki "RestAssured.baseURI" bir variable degil.-
    // -It is intimitian for the base URL= DEDI. ama bunu yazmazsam kod hicbir sekilde
    //calismiyor.
    // bir de swaggerdan aldigim adreste http:// kismi yoktu. bu postmande calisirken sorun
    //olmuyordu ama simdi postmende calismiyoruz. o yuzden adresin basina "http://" eklemeliyim.
    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    //We need to perform CRUD operations. We already have admin user.At first we need token.
    //bunun icin postmane gittik, oradan get token methodunu kullanarak yeni bir token olusturduk.
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NzY3MjczNjgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY3Njc3MDU2OCwidXNlcklkIjoiNDk1NCJ9.HXWL_s7kHvKktzSVxsdQu5l6pmhyyk-B6uCpoS1aJgo";

    @Test
    public void bgetOneEmployee(){
        System.out.println("2-Getting the employee that we created:");
        //prepare the request, we use RequestSpecification.
        //when we write given() it was red and when we right click on it. We saw "io.restassured.Restassured.given"
        //we need to import it.
        //after given(), we put "." it offers new methods and we chose "header()" inside this header method
        //we provide the key-value pairs:"Content-Type: application/json" and "Authorization:token" for these values I looked at postman-header
        //section.
        //we do not need any body part because we are getting an employee, but we need query parameters.
        RequestSpecification request= given().header("Authorization",token)
                                            .header("Content-Type","application/json")
                //daha once burada hardcoded olarak employee_id'yi cagiriyorduk: .queryParam("employee_id", "46159A")
                //fakat simdi olusturdugumuz employee'nin employee_id'sini direkt cektigimiz ve employee_id olarak
                //static variable olarak save ettigimiz icin burada artik onu kullanabiliriz.
                                            .queryParam("employee_id", employee_id);
        //we are done with creating the query, now we neeed to hit the end-point:
        //since my method is "get" we use get() after when().
        //Oncelikle sunu yazdik: request.when().get("/getOneEmployee.php");
        //bunun sonucu bir response olusacagi icin sol tarafa "Response response = " kismini ekledik.

        Response response = request.when().get("/getOneEmployee.php");

        //cikan sonucu yazdirmak icin asagidaki kodu yazdik ama bu classin icinde main method
        //yok. onun icin kodu run edemeyecegim. henuz tam olarak frameworke implement etmedigim icin de runner
        //classtan da cagiramayacagim. dolayisiyla baska cozum bulmam lazim. bunun icin methodun basina @Test ekledik.
        // bu anotation TestNG'den degil Junitten geliyor. Cunku bizim frameworkumuzde Junit var.
        //System.out.println(response.asString());
        //asagidaki kodda ayni sekilde bir ustteki kod gibi responsebodyi ekrana getiriyor: prettyprint()
        response.prettyPrint();

        //to verifying the status code: Eger kodu 201 olarak degistirirsek test failed oluyor.
        response.then().assertThat().statusCode(200);

        //using jsonPath() method, we are extracting a value from responsebody:
        String firstname = response.jsonPath().getString("employee.emp_firstname"); //name3
        System.out.println(firstname);

        //first way of assertion:
        Assert.assertEquals(firstname, "name3");

        //second way to assertion: Burada equalTo was red at the beginning.
        //sag tikladik ver "import static method"-->coreMatchers.equalTo secenegini sectik.
        //ama daha sonra secenekleri genisletmek icin import static org.hamcrest.Matchers.*; kismini
        //import ettik.
        response.then().assertThat().body("employee.emp_firstname",equalTo("name3"));

}

@Test
public void acreateEmployee() {
    System.out.println("1-Creating an employee:");
        RequestSpecification request = given().header("Authorization",token)
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

        Response response = request.when().post("/createEmployee.php");

        response.prettyPrint(); //printout the responseBody

         //verify the status code which is 201
         response.then().assertThat().statusCode(201);

        //I want to fetch the employee_id. so that later I can check this employee using its id.
        //Burada ust obje buyuk harfle basliyor: Employee Dolayisiyla burada "Employee.emp_id" yazacagim
        //"employee.emp_id" yerine.
         //Burada save edecegim id'yi baska yerlerde de kullanacagim icin bu variable static olarak tanimlamam gerekiyor.
        employee_id = response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
        //verify lastname:
        response.then().assertThat().body("Employee.emp_lastname",equalTo("lastName3"));
        //verify middle name:
        response.then().assertThat().body("Employee.emp_middle_name",equalTo("middleName3"));
        //verify something from response header in case we need to do it later:.
        // bunun icin postman'deki konsolu actik ve oradaki basliklara baktik, herhangi birini sectik.
        //BURASI ONEMLI!!!! header icin "equalTo" gerekli degil DEDI AMA YAZDIGIMDA DA SIKINTI OLMADI.
        response.then().assertThat().header("Server","Apache/2.4.39 (Win64) PHP/7.2.18");
}
//full update of an employee
    @Test
        public void cupdateEmployee() {
        System.out.println("3-We are updating the employee that we created:");
        RequestSpecification request = given().header("Authorization",token)
                .header("Content-Type","application/json")
                //burada bir hardcoded employee_id vardi, onu bizim static value ile degistirmemiz gerekiyordu.
                //onun icin sayiyi oldugu gibi sildik ve yerine "" yazdik arasina da ++ koyup bu artilarin arasina da
                //bizim static employee_id ifadesini yazdik.
                .body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Mark\",\n" +
                        "  \"emp_lastname\": \"Levin\",\n" +
                        "  \"emp_middle_name\": \"II\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2000-02-13\",\n" +
                        "  \"emp_status\": \"Promation\",\n" +
                        "  \"emp_job_title\": \"cloud engineer\"\n" +
                        "}");

            Response response = request.when().put("/updateEmployee.php");
            response.prettyPrint();
            //verify status code:
            response.then().assertThat().statusCode(200);
            //response bodyde yer alan mesajin dogrulugunu kontrol etmek istiyorum:
            response.then().assertThat().body("Message", equalTo("Employee record Updated"));
}
@Test
public void dgetUpdatedEmployee() {
    System.out.println("4-We are getting the updated employee:");
    RequestSpecification request= given().header("Authorization",token)
            .header("Content-Type","application/json")
            .queryParam("employee_id", employee_id);

    Response response = request.when().get("/getOneEmployee.php");
    response.prettyPrint();
    response.then().assertThat().statusCode(200);
    response.then().assertThat().body("employee.emp_firstname", equalTo("Mark"));
    response.then().assertThat().body("employee.emp_job_title", equalTo("Cloud Engineer"));
}
}
