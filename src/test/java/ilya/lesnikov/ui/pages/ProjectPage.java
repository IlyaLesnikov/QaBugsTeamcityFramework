package ilya.lesnikov.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ilya.lesnikov.ui.elements.ProjectElement;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectPage extends BasePage {
    private final static String PROJECT_ENDPOINT = "/project/%s";
    public SelenideElement title = $("[class*='ProjectPageHeader__title']");
    private final ElementsCollection projects = $$("[class*='Subproject__line']");
    private final SelenideElement header = $("[class*='BuildTypeLine__root']");

    public static ProjectPage open(String projectId) {

        return Selenide.open(PROJECT_ENDPOINT.formatted(projectId), ProjectPage.class);
    }

    public ProjectPage() {
        header.shouldBe(visible, BASE_WAITING);
    }


    public List<ProjectElement> getProjects() {
        return generatePageElements(projects, ProjectElement::new);
    }
}
