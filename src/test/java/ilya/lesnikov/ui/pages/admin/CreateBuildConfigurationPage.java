package ilya.lesnikov.ui.pages.admin;

import com.codeborne.selenide.Selenide;
import ilya.lesnikov.ui.pages.BuildConfigurationPage;

public class CreateBuildConfigurationPage extends CreateBasePage {
    private static final String PROJECT_SHOW_MODE = "createBuildTypeMenu";

    public static CreateBuildConfigurationPage open(String projectId) {
        return Selenide.open(CREATE_PROJECT_ENDPOINT.formatted(projectId, PROJECT_SHOW_MODE), CreateBuildConfigurationPage.class);
    }

    public CreateBuildConfigurationPage createForm(String url) {
        createProject(url);
        return Selenide.page(CreateBuildConfigurationPage.class);
    }

    public BuildConfigurationPage setupProject(String buildConfigurationName) {
        buildConfigurationNameInput.val(buildConfigurationName);
        proceedButton.click();

        return Selenide.page(BuildConfigurationPage.class);
    }
}
