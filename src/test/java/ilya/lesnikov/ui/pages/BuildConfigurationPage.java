package ilya.lesnikov.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BuildConfigurationPage extends BasePage {
    public final SelenideElement successMessage = $(".successMessage");
}
