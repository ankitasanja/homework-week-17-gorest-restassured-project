package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class PostsAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()

                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);

    }
    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("id",hasSize(25));
    }
    //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demittocentum.”
    @Test
    public void test002() {
        response.body("find { it.id == 38833 }.title" , equalTo("Carcer subito cohibeo tibi non utrimque bestia patior tredecim."));
    }
    //3. Check the single user_id in the Array list (5522)
    @Test
    public void test003() {
        response.body("user_id", hasItem(2223281));
    }
    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(38829,38826,38823));
    }
    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”
    @Test
    public void test005() {
        response.body("find { it.user_id == 2223281 }.body" , equalTo("Accipio articulus corrigo. Celo conicio suppellex. Antepono veniam victus. Caelum cultura eligendi. Coepi caecus casus. Id deserunt voluptas. Laborum suus delectus. Vociferor cimentarius nulla. Conforto tepesco contabesco. Autus qui vado. Aut nostrum velit. Attonbitus usus conatus. Bis colloco admoneo. Cernuus aut umbra. Denique solitudo aspicio."));
    }

}
