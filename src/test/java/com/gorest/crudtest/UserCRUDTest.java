package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static int userId;
    static String name = "testprime" + TestUtils.getRandomValue();
    static String gender = "male";
    static String email = TestUtils.getRandomValue() + "testprime@gmail.com";
    static String status = "active";


    @Test
    public void test001() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender(gender);
        userPojo.setEmail(email);
        userPojo.setStatus(status);

        Response response = given()
                .header("Content-Type", "application.json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post().then().extract().response();
        response.then().log().all().statusCode(201);
        // Get status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        userId = response.jsonPath().get("id");
        System.out.println("Id is:" + userId);
    }

    @Test
    public void test002() {
        String token = "61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/" + userId);
        response.then().log().all().statusCode(200);
        // Get status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);


    }

    @Test
    public void test003() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setGender("female");
        userPojo.setEmail(email);
        userPojo.setStatus("inactive");

        Response response = given()
                .header("Content-Type", "application.json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .put("/" + userId);
        response.then().log().all().statusCode(200);
        // Get status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);

    }

    @Test
    public void test004() {
        String token = "61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215";
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("/" + userId);
        response.then().log().all().statusCode(204);
        // Get status code
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }
}
