package ilya.lesnikov.ui.pages.admin;

import com.codeborne.selenide.SelenideElement;
import ilya.lesnikov.ui.pages.BasePage;

import static com.codeborne.selenide.Selenide.$;

public abstract class CreateBasePage extends BasePage {
    protected static final String CREATE_PROJECT_ENDPOINT = "/createObjectMenu.html?projectId=%s&showMode=%s";
    protected final SelenideElement urlInput = $("#url");
    protected final SelenideElement proceedButton = $("#createFromUrlForm .submitButton ");

    protected  CreateBasePage createProject(String url) {
        urlInput.val(url);
        proceedButton.click();

        return this;
    }
}
