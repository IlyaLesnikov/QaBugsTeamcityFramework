package ilya.lesnikov.api.tests;

import ilya.lesnikov.api.models.Project;
import ilya.lesnikov.api.requests.CheckedRequest;
import ilya.lesnikov.api.requests.UncheckedRequest;
import ilya.lesnikov.api.spec.Specifications;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import java.net.HttpURLConnection;
import static ilya.lesnikov.api.enums.Endpoint.PROJECTS;
import static ilya.lesnikov.api.enums.Endpoint.USERS;
import static ilya.lesnikov.api.generatos.TestDataGenerator.generate;

@Tag("regress")
@DisplayName("Тесты к компоненту создания проектов")
public class ProjectTests extends BaseTest {

    @Test
    @DisplayName("Создание проекта с валидными данными")
    public void creatingProjectWithValidDataTest() {
        superUserCheckedRequest
                .getRequest(USERS)
                .create(testData.getUser());

        var userCheckRequest = new CheckedRequest(Specifications.authSpec(testData.getUser()));

        var createProject = userCheckRequest
                .<Project>getRequest(PROJECTS)
                .create(testData.getProject());

        softAssertions.assertThat(createProject.getName()).isEqualTo(testData.getProject().getName());
        softAssertions.assertThat(createProject.getId()).isEqualTo(testData.getProject().getId());
    }

    @Test
    @Tags({@Tag("Negative"), @Tag("CRUD")})
    @DisplayName("Создание существующего проекта")
    public void creatingExistingProjectTest() {
        var expectedResult = """
                Responding with error, status code: 400 (Bad Request).
                Details: jetbrains.buildServer.serverSide.DuplicateProjectNameException: Project with this name already exists: %s
                Error occurred while processing this request.
                """.trim().formatted(testData.getProject().getName());

        superUserCheckedRequest
                .getRequest(USERS)
                .create(testData.getUser());

        var userCheckRequest = new UncheckedRequest(Specifications.authSpec(testData.getUser()));

        userCheckRequest
                .getRequest(PROJECTS)
                .create(testData.getProject());
        userCheckRequest
                .getRequest(PROJECTS)
                .create(testData.getProject())
                .then()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body(Matchers.is(expectedResult));
    }

    @Test
    @Tags({@Tag("Negative"), @Tag("CRUD")})
    @DisplayName("Создание проекта с пустым Id")
    public void creatingProjectWithEmptyId() {
        var expectedResult = """
                Error has occurred during request processing, status code: 500 (Internal Server Error).
                Details: jetbrains.buildServer.serverSide.InvalidIdentifierException: Project ID must not be empty.
                Error occurred while processing this request.
                """.trim().formatted(testData.getProject().getName());
        var createProject = generate(Project.class, "");
        var userCheckRequest = new UncheckedRequest(Specifications.authSpec(testData.getUser()));

        superUserCheckedRequest
                .getRequest(USERS)
                .create(testData.getUser());

        userCheckRequest
                .getRequest(PROJECTS)
                .create(createProject)
                .then().statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
                .body(Matchers.is(expectedResult));
    }
}
