import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import models.User;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    User user = new User();

    @BeforeAll
    static void setUp() {
        String browser = System.getProperty("browser", "chrome");
        String version = System.getProperty("version", "91");
        String size = System.getProperty("size", "1920x1080");

        Configuration.baseUrl = "https://demoqa.com";

        Configuration.browser = browser;
        Configuration.browserVersion = version;
        Configuration.browserSize = size;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("allure", new AllureSelenide());

    }

}
