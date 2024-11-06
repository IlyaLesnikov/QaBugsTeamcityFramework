package ilya.lesnikov.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProjectPage extends BasePage {
    private final static String PROJECT_ENDPOINT = "/project/%s";
    public SelenideElement title = $("[class*='ProjectPageHeader__title']");

    public static ProjectPage open(String projectId) {

        return Selenide.open(PROJECT_ENDPOINT.formatted(projectId), ProjectPage.class);
    }
}
