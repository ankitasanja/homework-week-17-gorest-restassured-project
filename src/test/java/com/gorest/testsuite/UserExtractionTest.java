package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()

                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);

    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> id = response.extract().path("id");
        System.out.println(id);
    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> name = response.extract().path("name");
        System.out.println("All names are: " + name);
    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("name[4]");
        System.out.println("Name of 5th object is " + name);
    }


    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> nameOfObjects = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("names of all object whose status = inactive are " + nameOfObjects);
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("gender of all the object whose status = active are " + gender);
    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> name = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("names of the object whose gender = female are " + name);
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("all the emails of the object where status = inactive are " + email);
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<Integer> id = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("the ids of the object where gender = male are " + id);
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<String> status = response.extract().path("status");
        System.out.println("All status " + status);
    }

    //10. Get email of the object where name = Karthik Dubashi IV
    @Test
    public void test010() {
        List<String> email = response.extract().path("findAll{it.name == 'Chandni Trivedi'}.email");
        System.out.println("email of the object where name = Chandni Trivedi IV are " + email);
    }

    //11. Get gender of id = 5471
    @Test
    public void test011() {
        String gender = response.extract().path("find{it.id==2223221}.gender");
        System.out.println(" gender of id = 5471" + gender);
    }
}
