import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static helpers.Spec.request;
import static helpers.Spec.responseSpec;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReqResTests extends TestBase{

    //разобраться как передать в спеку в ответ логи
    //как передать параметры в ответ
    @BeforeAll
    static void setup() {
        RestAssured.filters(withCustomTemplates());
    }

    @Test
    @DisplayName("Вывести список пользователей")
    void listUsersTest() {

        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .body("data.last_name", hasItems("Lawson", "Ferguson"))
                .body("data.first_name", hasItems("Byron", "George", "Rachel"))
                .body("data.avatar", hasItem("https://reqres.in/img/faces/11-image.jpg"))
                .body("$", hasKey("page"))
                .body("data", everyItem(hasKey("email")));
    }

    @Test
    @DisplayName("Вывести список пользователей (AssertJ)")
    void listUsersTestWithAssertJAndModels() {

        Response response = given()
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
    @DisplayName("Вывести список ресурсов (AssertJ)")
    void listResourseTestWithAssertJ() {
        Response response = given()
                .spec(request)
                .get("/unknown")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().response();

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Создать пользователя")
    void createUserTest() {

        user.setName("morpheus");
        user.setJob("zion resident");

        given()
                .spec(request)
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
    @DisplayName("Зарегистрировать пользователя")
    void registerUserTest() {

        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    @DisplayName("Обновить пользователя")
    void updateUserTest() {

        user.setName("morpheus");
        user.setJob("zion resident");

        given()
                .spec(request)
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
    @DisplayName("Пользователь не найден")
    void userNotFoundTest() {

        given()
                .spec(request)
                .get("/unknown/23")
                .then()
                .log().status()
                .log().body()
                .statusCode(404);

    }

    @Test
    @DisplayName("Авторизация")
    void successfulLogin() {

        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec)
                .log().status()
                .log().body()
                .body("token", is(notNullValue()));
    }

}
