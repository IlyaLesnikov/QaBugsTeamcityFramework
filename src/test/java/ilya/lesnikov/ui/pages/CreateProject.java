package ilya.lesnikov.ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CreateProject extends BasePage{
    private final SelenideElement urlInput = $("#url");
    private final SelenideElement proceedButton = $("#createFromUrlForm .submitButton ");

    public CreateProject createProject() {
        urlInput.val("");
        proceedButton.click();

        return this;
    }
}
