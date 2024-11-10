package ilya.lesnikov.ui.tests;

import com.codeborne.selenide.Condition;
import ilya.lesnikov.api.enums.Endpoint;
import ilya.lesnikov.api.models.Project;
import ilya.lesnikov.ui.pages.ProjectPage;
import ilya.lesnikov.ui.pages.ProjectsPage;
import ilya.lesnikov.ui.pages.admin.CreateProjectPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

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
        loginAs(testData.getUser());

        CreateProjectPage.open("_Root")
            .createForm(REPO_URL)
            .setupProject(testData.getProject().getName(), testData.getBuildType().getName());

        var createdProject = superUserCheckedRequest.<Project>getRequest(Endpoint.PROJECTS).read("name:%s".formatted(testData.getProject().getName()));
        assertNotNull(createdProject);

        ProjectPage.open(createdProject.getId()).title.shouldHave(Condition.exactText(testData. getProject().getName()));

        var projectExist = ProjectsPage.open().getProjects().stream().anyMatch(project -> project.getName().text().equals(testData.getProject().getName()));

        assertTrue(projectExist);
    }
}
