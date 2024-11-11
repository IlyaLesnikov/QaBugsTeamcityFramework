package ilya.lesnikov.ui.pages.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CreateBuildConfigurationPage extends CreateBasePage {
    private static final String PROJECT_SHOW_MODE = "createBuildTypeMenu";
    public final SelenideElement buildConfigurationNameError = $("#error_buildTypeName");

    public static CreateBuildConfigurationPage open(String projectId) {
        return Selenide.open(CREATE_PROJECT_ENDPOINT.formatted(projectId, PROJECT_SHOW_MODE), CreateBuildConfigurationPage.class);
    }

    public CreateBuildConfigurationPage createForm(String url) {
        createProject(url);
        return this;
    }

    public void setupProject(String buildConfigurationName) {
        buildConfigurationNameInput.val(buildConfigurationName);
        proceedButton.click();
    }
}
