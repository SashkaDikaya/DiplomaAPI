import models.LombokUserData;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.User;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static helpers.Spec.request;
import static helpers.Spec.responseSpec;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HomeWorkTests extends TestBase{

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        RestAssured.filters(withCustomTemplates());
    }



    @Test
    @DisplayName("")
    void listUsersTest1() {



        LombokUserData data = given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .spec(responseSpec)
                .log().status()
                .log().body()
                .extract().as(LombokUserData.class);



        //data.getUser().getLastName().contains("Lawson");

        /*LombokUserData.body("data.last_name", hasItems("Lawson", "Ferguson"))
                .body("data.first_name", hasItems("Byron", "George", "Rachel"))
                .body("data.avatar", hasItem("https://reqres.in/img/faces/11-image.jpg"))
                .body("$", hasKey("page"))
                .body("data", everyItem(hasKey("email")));*/



        //assertThat(data.getUser().getLastName().contains("Lawson"));




    }



    @Test
    @DisplayName("")
    void listUsersTest() {

        given()
                .contentType(JSON)
                .log().uri()
                .log().body()
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.last_name", hasItems("Lawson", "Ferguson"))
                .body("data.first_name", hasItems("Byron", "George", "Rachel"))
                .body("data.avatar", hasItem("https://reqres.in/img/faces/11-image.jpg"))
                .body("$", hasKey("page"))
                .body("data", everyItem(hasKey("email")));
    }

    @Test
    @DisplayName("")
    void listUsersTestWithAssertJAndModels() {

        Response response =
                given()
                        .spec(request)
                        .get("/users?page=2")
                        .then()
                        .log().status()
                        .log().body()
                        .spec(responseSpec)
                        .extract().response();

        int total = response.path("total");
        int page = response.path("page");

        assertEquals(12, total);
        assertEquals(2, page);
    }

    @Test
    void listUsersTestWithAssertJ() {
        Response response = get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        int total = response.path("total");
        int page = response.path("page");

        assertEquals(12, total);
        assertEquals(2, page);

    }

    @Test
    void listResourseTestWithAssertJ() {
        Response response = get("/unknown")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        assertThat(response).isNotNull();

    }

    @Test
    void createUserTest() {

        user.setName("morpheus");
        user.setJob("zion resident");

        given()
                .contentType(JSON)
                .log().uri()
                .log().body()
                .body(user)
                .when()
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is(user.getName()))
                .body("job", is(user.getJob()));
    }

    @Test
    void registerUserTest() {

        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");

        given()
                .contentType(JSON)
                .log().uri()
                .log().body()
                .body(user)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void updateUserTest() {

        user.setName("morpheus");
        user.setJob("zion resident");

        given()
                .contentType(JSON)
                .log().uri()
                .log().body()
                .body(user)
                .when()
                .post("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("job", is(user.getJob()));
    }

    @Test
    void userNotFoundTest() {

        given()
                .contentType(JSON)
                .get("/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);

    }

    @Test
    void successfulLogin() {

        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .body("token", is(notNullValue()));
    }

}
