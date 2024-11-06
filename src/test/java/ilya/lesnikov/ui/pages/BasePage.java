package ilya.lesnikov.ui.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ilya.lesnikov.ui.elements.BasePageElement;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public abstract class BasePage {
    protected static final Duration BASE_WAITING = Duration.ofSeconds(30000);

    protected <T extends BasePageElement> List<T> generatePageElements(ElementsCollection elements, Function<SelenideElement, T> creator) {
        return elements.stream().map(creator).toList();
    }
}
