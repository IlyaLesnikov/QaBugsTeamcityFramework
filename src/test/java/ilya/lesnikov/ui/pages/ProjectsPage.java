package ilya.lesnikov.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import ilya.lesnikov.ui.elements.ProjectElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class ProjectsPage extends BasePage {
    private static final String PROJECTS_ENDPOINT = "/favorite/projects";
    private final ElementsCollection projects = $$("[class*='Subproject__line']");

    public static ProjectsPage open() {
        return Selenide.open(PROJECTS_ENDPOINT, ProjectsPage.class);
    }


    public List<ProjectElement> getProjects() {
        return generatePageElements(projects, ProjectElement::new);
    }
}
