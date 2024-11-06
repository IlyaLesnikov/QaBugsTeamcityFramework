package ilya.lesnikov.ui.pages.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class CreateProjectPage extends CreateBasePage {
    private static final String PROJECT_SHOW_MODE = "createProjectMenu";
    private final SelenideElement projectNameInput = $("#projectName");

    public static CreateProjectPage open(String projectId) {
        return Selenide.open(CREATE_PROJECT_ENDPOINT.formatted(projectId, PROJECT_SHOW_MODE), CreateProjectPage.class);
    }

    public CreateProjectPage createForm(String url) {
        createProject(url);
        return Selenide.page(CreateProjectPage.class);
    }

    public CreateProjectPage setupProject(String projectName, String buildConfigurationName) {
        projectNameInput.val(projectName);
        buildConfigurationNameInput.val(buildConfigurationName);
        proceedButton.click();

        return Selenide.page(CreateProjectPage.class);
    }
}
