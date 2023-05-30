package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()

                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);

    }
    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("id",hasSize(20));
    }
    //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
    @Test
    public void test002() {
        response.body("find { it.id == 2223226 }.name" , equalTo("Krishnadas Chaturvedi"));
    }
    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {
        response.body("name", hasItem("Chandni Trivedi"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan Guha, Karthik Dubashi IV)
    @Test
    public void test004() {
        response.body("name", hasItems("Deeptiman Varma", "Dharitri Khatri", "Satyen Dutta"));
    }
    //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005() {
        response.body("find { it.id == 2223229 }.email" , equalTo("deeptiman_varma@stoltenberg-olson.test"));
    }
    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {
        response.body("find { it.name == 'Aatreya Shah' }.status" , equalTo("active"));
    }
    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007() {
        response.body("find { it.name == 'Krishnadas Chaturvedi' }.gender" , equalTo("female"));
    }

}
