package ilya.lesnikov.ui.pages.admin;

import com.codeborne.selenide.Selenide;


public class CreateProjectPage extends CreateBasePage {
    private static final String PROJECT_SHOW_MODE = "createProjectMenu";

    public static CreateProjectPage open(String projectId) {
        return Selenide.open(CREATE_PROJECT_ENDPOINT.formatted(projectId, PROJECT_SHOW_MODE), CreateProjectPage.class);
    }
}
