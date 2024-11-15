package ilya.lesnikov.ui.tests;

import com.codeborne.selenide.Condition;
import ilya.lesnikov.api.models.BuildType;
import ilya.lesnikov.api.models.Project;
import ilya.lesnikov.api.requests.CheckedRequest;
import ilya.lesnikov.api.requests.UncheckedRequest;
import ilya.lesnikov.api.spec.Specifications;
import ilya.lesnikov.ui.pages.BuildPage;
import ilya.lesnikov.ui.pages.ProjectsPage;
import ilya.lesnikov.ui.pages.admin.CreateBuildConfigurationPage;
import org.junit.jupiter.api.*;
import static ilya.lesnikov.api.data.Constants.REPO_URL;
import static ilya.lesnikov.api.enums.Endpoint.BUILD_TYPES;
import static ilya.lesnikov.api.enums.Endpoint.PROJECTS;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("regression")
@DisplayName("Тесты на создания билд конфигурации")
public class CreateBuildConfigurationTests extends BaseUiTest {
    @Test
    @Tags({@Tag("smoke"), @Tag("positive")})
    @DisplayName("Создания билд конфигурации со всеми валидными полями")
    public void creatingBuildConfigurationWithAllValidFields() {
        loginAs(testData.getUser());

        var userCheckRequest = new CheckedRequest(Specifications.superUserAuthSpec());
        userCheckRequest
                .<Project>getRequest(PROJECTS)
                .create(testData.getProject());

        CreateBuildConfigurationPage.open(testData.getProject().getId())
                .createForm(REPO_URL)
                .setupProject(testData.getBuildType().getName());

        assertNotNull(userCheckRequest
                .<BuildType>getRequest(BUILD_TYPES)
                .read("name:%s".formatted(testData.getBuildType().getName())));

        ProjectsPage.open()
                .getProjects()
                .stream()
                .findFirst()
                .get().getLink().click();

        new BuildPage()
                .getBuilds()
                .stream()
                .findFirst()
                .get().getName().shouldHave(Condition.exactText(testData.getBuildType().getName()));
    }

    @Test
    @Tags({@Tag("smoke"), @Tag("negative")})
    @DisplayName("Создания билд конфигурации с пустым обязательным полем")
    public void creatingBuildConfigurationWithEmptyRequiredField() {
        loginAs(testData.getUser());

        var userCheckRequest = new CheckedRequest(Specifications.superUserAuthSpec());
        userCheckRequest
                .<Project>getRequest(PROJECTS)
                .create(testData.getProject());

        CreateBuildConfigurationPage.open(testData.getProject().getId())
                .createForm(REPO_URL)
                .setupProject("");

        new UncheckedRequest(Specifications.superUserAuthSpec()).getRequest(BUILD_TYPES).read(testData.getBuildType().getId()).then().statusCode(404);
        new CreateBuildConfigurationPage().buildConfigurationNameError.shouldHave(Condition.exactText("Build configuration name must not be empty"));
    }
}
