package ilya.lesnikov.api.tests;

import ilya.lesnikov.api.models.BuildType;
import ilya.lesnikov.api.models.Project;
import ilya.lesnikov.api.requests.CheckedRequest;
import ilya.lesnikov.api.requests.UncheckedRequest;
import ilya.lesnikov.api.spec.Specifications;
import ilya.lesnikov.api.utils.AssertionUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import java.net.HttpURLConnection;
import java.util.Arrays;

import static ilya.lesnikov.api.enums.Endpoint.*;
import static ilya.lesnikov.api.generatos.TestDataGenerator.generate;
import static ilya.lesnikov.api.utils.AssertionUtils.assertEquals;
import static io.qameta.allure.Allure.step;

@Tag("Regress")
@DisplayName("")
public class BuildTypeTest extends BaseTest {

    @Test
    @Tags({ @Tag("Positive"), @Tag("CRUD") })
    @DisplayName("Создание нового билда")
    public void test1() {
        superUserCheckedRequest
                .getRequest(USERS)
                .create(testData.getUser());

        var userCheckRequest = new CheckedRequest(Specifications.authSpec(testData.getUser()));

        userCheckRequest
                .<Project>getRequest(PROJECTS)
                .create(testData.getProject());

        userCheckRequest
                .getRequest(BUILD_TYPES)
                .create(testData.getBuildType());

        var createBuildType = userCheckRequest
                .<BuildType>getRequest(BUILD_TYPES)
                .read(testData.getBuildType().getId());

        assertEquals(testData.getBuildType().getName(), createBuildType.getName());
    }

    @Test
    @Tags({@Tag("Negative"), @Tag("CRUD")})
    @DisplayName("Создание существующего билда")
    public void test2() {
        var buildTypeWithSameId = generate(Arrays.asList(testData.getProject()), BuildType.class, testData.getBuildType().getId());

        superUserCheckedRequest.getRequest(USERS).create(testData.getUser());

        var userCheckRequests = new CheckedRequest(Specifications.authSpec(testData.getUser()));

        userCheckRequests.<Project>getRequest(PROJECTS).create(testData.getProject());

        userCheckRequests.getRequest(BUILD_TYPES).create(testData.getBuildType());

        var expectedResult = """
                Responding with error, status code: 400 (Bad Request).
                Details: jetbrains.buildServer.serverSide.identifiers.DuplicateExternalIdException: The build configuration / template ID "%s" is already used by another configuration or template
                Error occurred while processing this request.""".formatted(testData.getBuildType().getId());

        new UncheckedRequest(Specifications.authSpec(testData.getUser()))
                .getRequest(BUILD_TYPES)
                .create(buildTypeWithSameId)
                .then().statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body(Matchers.is(expectedResult));
    }

    @Test
    @Tags({@Tag("Positive"), @Tag("Role")})
    @DisplayName("Создание билда под админом")
    public void test3() {
        step("Создание юзера");
        step("Создание проекта");
        step("Назначение роли PROJECT_ADMIN на проект1");

        step("Создание билда");
        step("Создание проекта1");
        step("Назначение роли PROJECT_ADMIN на проект1");

    }

    @Test
    @Tags({@Tag("Negative"), @Tag("Role")})
    @DisplayName("Создание билда под админом")
    public void test4() {
        step("Создание юзера");
        step("Создание проекта");
        step("Назначение роли PROJECT_ADMIN на проект1");

        step("Создание билда");
        step("Создание проекта1");
        step("Назначение роли PROJECT_ADMIN на проект1");

    }
}
