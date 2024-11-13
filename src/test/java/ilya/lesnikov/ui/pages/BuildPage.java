package ilya.lesnikov.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ilya.lesnikov.ui.elements.BuildElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuildPage extends BasePage {
    private static final String BUILD_ENDPOINT = "/project/%s/mode=builds";
    private ElementsCollection buildElements = $$("[class*='BuildTypeLine__buildTypeName']");
    private SelenideElement header = $(".MainPanel__router--gF > div");

    public static BuildPage open(String id) {
        return Selenide.open(BUILD_ENDPOINT.formatted(id), BuildPage.class);
    }

    public BuildPage() {
        header.shouldBe(Condition.visible, BASE_WAITING);
    }

    public List<BuildElement> getBuilds() {
        return generatePageElements(buildElements, BuildElement::new);
    }
}
