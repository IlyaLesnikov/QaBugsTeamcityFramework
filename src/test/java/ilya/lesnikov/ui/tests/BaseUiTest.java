package ilya.lesnikov.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ilya.lesnikov.BaseTest;
import ilya.lesnikov.api.config.Config;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

abstract public class BaseUiTest extends BaseTest {

    @BeforeAll
    protected static void setupUiTests() {
        Configuration.browser = Config.getProperties("browser");
        Configuration.baseUrl = Config.getProperties("host");
        Configuration.remote = Config.getProperties("remote");
        Configuration.browserSize = Config.getProperties("browserSize");
        Configuration.browserCapabilities.setCapability("selenoid:options",
                Map.of("enableVNC", true,
                       "enableLog", true));
    }

    @AfterEach
    public void closedWebDriver() {
        Selenide.closeWebDriver();
    }
}
