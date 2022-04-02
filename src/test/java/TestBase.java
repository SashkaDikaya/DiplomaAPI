import io.restassured.RestAssured;
import models.User;
import org.junit.jupiter.api.BeforeAll;

import static helpers.CustomAllureListener.withCustomTemplates;

public class TestBase {

    User user = new User();

    @BeforeAll
    static void setUp() {
        RestAssured.filters(withCustomTemplates());

    }

}
