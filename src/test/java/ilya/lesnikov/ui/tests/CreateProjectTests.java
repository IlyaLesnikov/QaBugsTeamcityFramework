package ilya.lesnikov.ui.tests;

import com.codeborne.selenide.Condition;
import ilya.lesnikov.api.enums.Endpoint;
import ilya.lesnikov.api.models.Project;
import ilya.lesnikov.ui.pages.ProjectPage;
import ilya.lesnikov.ui.pages.ProjectsPage;
import ilya.lesnikov.ui.pages.admin.CreateProjectPage;
import org.junit.jupiter.api.*;
import static ilya.lesnikov.api.data.Constants.REPO_URL;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("regress")
@DisplayName("Проверки компонента создания проекта")
public class CreateProjectTests extends BaseUiTest {
    @Test
    @Tags({@Tag("positive"), @Tag("smoke")})
    @DisplayName("Создание проекта пользователем")
    public void userCreateProjectTest() {
        loginAs();

        CreateProjectPage.open("_Root")
            .createForm(REPO_URL)
            .setupProject(testData.getProject().getId(), testData.getBuildType().getName());

        var createdProject = superUserCheckedRequest.<Project>getRequest(Endpoint.PROJECTS).read("name:%s".formatted(testData.getProject().getName()));
        assertNotNull(createdProject);

        ProjectPage.open(testData.getProject().getId()).title.shouldHave(Condition.exactText(testData.getProject().getId()));

        var projectExist = ProjectsPage.open().getProjects().stream().anyMatch(element -> element.getName().getText().equals(testData.getProject().getName()));

        assertTrue(projectExist);
    }
}
