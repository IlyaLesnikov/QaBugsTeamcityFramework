package ilya.lesnikov.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ilya.lesnikov.ui.elements.ProjectElement;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProjectsPage extends BasePage {
    private static final String PROJECTS_ENDPOINT = "/favorite/projects";
    private final ElementsCollection projects = $$("[class*='Subproject__line']");
    private final SelenideElement header = $(".MainPanel__router--gF > div");

    public static ProjectsPage open() {
        return Selenide.open(PROJECTS_ENDPOINT, ProjectsPage.class);
    }

    public ProjectsPage() {
        header.shouldBe(visible, BASE_WAITING);
    }


    public List<ProjectElement> getProjects() {
        return generatePageElements(projects, ProjectElement::new);
    }
}
