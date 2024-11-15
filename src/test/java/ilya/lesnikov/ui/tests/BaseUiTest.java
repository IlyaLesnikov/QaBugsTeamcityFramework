package ilya.lesnikov.ui.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import ilya.lesnikov.BaseTest;
import ilya.lesnikov.api.config.Config;
import ilya.lesnikov.api.models.User;
import ilya.lesnikov.ui.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

import static ilya.lesnikov.api.enums.Endpoint.USERS;

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

    protected void loginAs(User user) {
        superUserCheckedRequest.getRequest(USERS).create(testData.getUser());
        LoginPage.open().login(user);
    }
}
