import io.restassured.response.Response;
import models.LombokUserData;
import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static helpers.Spec.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReqResTests extends TestBase {

    @Test
    @DisplayName("Поиск информации по id пользователя")
    void oneUserTest() {
        LombokUserData response = given()
                .spec(request)
                .when()
                .get("/users/7")
                .then()
                .spec(responseSpec200)
                .extract().as(LombokUserData.class);

        assertEquals("Michael", response.getUser().getFirstName());
        assertEquals("Lawson", response.getUser().getLastName());
        assertEquals("https://reqres.in/img/faces/7-image.jpg", response.getUser().getAvatar());
    }

    @Test
    @DisplayName("Вывести список пользователей")
    void listUsersTest1() {
        Response response = given()
                .spec(request)
                .get("/users?page=2")
                .then()
                .spec(responseSpec200)
                .extract().response();

        int total = response.path("total");
        int page = response.path("page");

        assertEquals(12, total);
        assertEquals(2, page);
    }

    @Test
    @DisplayName("Вывести список ресурсов")
    void listResourseTest() {
        Response response = given()
                .spec(request)
                .get("/unknown")
                .then()
                .spec(responseSpec200)
                .extract().response();

        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("Создание пользователя")
    void createUserTest() {
        User user = new User();
        user.setFirstName("morpheus");
        user.setJob("zion resident");

        User response = given()
                .spec(request)
                .body(user)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec201)
                .extract().as(User.class);

        assertEquals(user.getFirstName(), response.getFirstName());
        assertEquals(user.getJob(), response.getJob());
    }

    @Test
    @DisplayName("Регистрация пользователя")
    void registerUserTest() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("pistol");

        User response = given()
                .spec(request)
                .body(user)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec200)
                .extract().as(User.class);

        assertEquals(response.getToken(), "QpwL5tke4Pnpja7X4");
    }

    @Test
    @DisplayName("Обновление информации пользователя")
    void updateUserTest() {
        User user = new User();
        user.setFirstName("morpheus");
        user.setJob("zion resident");

        User response = given()
                .spec(request)
                .body(user)
                .when()
                .post("/users/2")
                .then()
                .spec(responseSpec201)
                .extract().as(User.class);

        assertEquals(response.getJob(), user.getJob());
    }

    @Test
    @DisplayName("Поиск несуществующего пользователя")
    void userNotFoundTest() {
        given()
                .spec(request)
                .get("/unknown/23")
                .then()
                .spec(responseSpec404);
    }

    @Test
    @DisplayName("Успешная авторизация пользователя")
    void successfulLogin() {
        User user = new User();
        user.setEmail("eve.holt@reqres.in");
        user.setPassword("cityslicka");

        User response = given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec200)
                .extract().as(User.class);

        assertEquals(response.getToken(), "QpwL5tke4Pnpja7X4");
    }
}