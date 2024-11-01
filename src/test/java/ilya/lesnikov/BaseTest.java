package ilya.lesnikov;

import ilya.lesnikov.api.config.Config;
import ilya.lesnikov.api.generatos.TestDataStorage;
import ilya.lesnikov.api.models.TestData;
import ilya.lesnikov.api.requests.CheckedRequest;
import ilya.lesnikov.api.spec.Specifications;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static ilya.lesnikov.api.generatos.TestDataGenerator.generate;

public class BaseTest {
    protected SoftAssertions softAssertions;
    protected CheckedRequest superUserCheckedRequest = new CheckedRequest(Specifications.superUserAuthSpec());
    protected TestData testData;

    @BeforeAll
    public static void beforeTestAll() {
        RestAssured.baseURI = Config.getProperties("host");
        RestAssured.port = Integer.parseInt(Config.getProperties("port"));
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter()
        );
    }

    @BeforeEach
    public void beforeTest() {
        softAssertions = new SoftAssertions();
        testData = generate();
    }

    @AfterEach
    public void afterTest() {
        softAssertions.assertAll();

        TestDataStorage.getInstance().deleteCreatedEntities();
    }
}
