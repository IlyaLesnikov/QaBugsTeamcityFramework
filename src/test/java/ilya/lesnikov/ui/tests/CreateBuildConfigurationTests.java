package ilya.lesnikov.ui.tests;

import com.codeborne.selenide.Condition;
import ilya.lesnikov.api.models.Project;
import ilya.lesnikov.api.requests.CheckedRequest;
import ilya.lesnikov.api.spec.Specifications;
import ilya.lesnikov.ui.pages.admin.CreateBuildConfigurationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static ilya.lesnikov.api.data.Constants.REPO_URL;
import static ilya.lesnikov.api.enums.Endpoint.PROJECTS;
import static ilya.lesnikov.api.enums.Endpoint.USERS;

@Tag("regression")
@DisplayName("Тесты на создания билд конфигурации")
public class CreateBuildConfigurationTests extends BaseUiTest {
    @Test
    @Tags({@Tag("smoke"), @Tag("positive")})
    @DisplayName("Создания билд конфигурации со всеми валидными полями")
    public void creatingBuildConfigurationWithAllValidFields() {
        superUserCheckedRequest
                .getRequest(USERS)
                .create(testData.getUser());
        var userCheckRequest = new CheckedRequest(Specifications.authSpec(testData.getUser()));
        userCheckRequest
                .<Project>getRequest(PROJECTS)
                .create(testData.getProject());

        loginAs();

        CreateBuildConfigurationPage.open(testData.getProject().getId())
                .createForm(REPO_URL)
                .setupProject("random")
                .successMessage.shouldHave(Condition.exactText("New project \"Spring Core For Qa (2)\", build configuration \"DBhb dfdf\" and VCS root \"%s\" have been successfully created.".formatted(REPO_URL)));


    }
}
