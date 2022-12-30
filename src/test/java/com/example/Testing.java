package com.example;

import com.example.excelHandling.ExcelData;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Testing {

    ExcelData excelData;

    @Test
    public void getEmployeeDetails() throws JSONException, IOException {

        //specify the baseURI of the restful webservice
        RestAssured.baseURI="http://localhost:9081/api/v1/employees";

        JSONObject jsonObject  = new JSONObject();
        jsonObject.put("first_name",excelData.getData(2,0));
        jsonObject.put("last_name",excelData.getData(2,1));
        jsonObject.put("email",excelData.getData(2,2));

        Response response=RestAssured.given().contentType(ContentType.JSON).accept(ContentType.JSON).body(jsonObject.toString()).when().post();
        RequestSpecification httpRequest = RestAssured.given();

        JsonPath jsonPathEvaluator = response.jsonPath();
        //specify the method type (Get) and the parameters
        //here we are going to use get so no parameters

        String Name = jsonPathEvaluator.get("last_name");
        Assert.assertEquals(Name, "j");
        System.out.println(response.getStatusCode());

    }

    @Test
    public  void deleteRequest(){
        RestAssured.baseURI="http://localhost:9081/api/v1/employees";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.delete("/8");

        System.out.println(response.getStatusCode());
    }

    @Test
    public void getEmpControllerTest1() {
        RestAssured.baseURI = "http://localhost:9081/api/v1/employees";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
        String statusLine = response.getStatusLine();
        Assert.assertEquals(statusLine, "HTTP/1.1 200 ");
        System.out.println("status received  " + response.getStatusLine());
        System.out.println("response " + response.prettyPrint());
    }

    @Test
    public void getEmpControllerTest2() {
        RestAssured.baseURI = "http://localhost:9081/api/v1/employees";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/4");
        ResponseBody body = response.getBody();
        String bodyAsString = body.asString();
        Assert.assertEquals(bodyAsString.contains("email"), true);
    }

    @Test
    public void getEmpControllerTestUsingJsonPathGet() {
        RestAssured.baseURI = "http://localhost:9081/api/v1/employees";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/6");
        ResponseBody body = response.getBody();
        JsonPath jsonPathEvaluator = response.jsonPath();
        String firstName = jsonPathEvaluator.get("first_name");
        Assert.assertEquals(firstName, "alihka");
    }


}
