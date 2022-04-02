import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import models.User;
import org.junit.jupiter.api.BeforeAll;

import static helpers.CustomAllureListener.withCustomTemplates;

public class TestBase {

    User user = new User();

    @BeforeAll
    static void setUp() {
        RestAssured.filters(withCustomTemplates());
        String browser = System.getProperty("browser", "chrome");
        String version = System.getProperty("version", "91");
        String size = System.getProperty("size", "1920x1080");

        //Configuration.baseUrl = "https://demoqa.com";

        Configuration.browser = browser;
        Configuration.browserVersion = version;
        Configuration.browserSize = size;

    }

}
