package ilya.lesnikov.ui.pages.admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ilya.lesnikov.ui.pages.BasePage;
import static com.codeborne.selenide.Selenide.$;

public abstract class CreateBasePage extends BasePage {
    protected static final String CREATE_PROJECT_ENDPOINT = "/admin/createObjectMenu.html?projectId=%s&showMode=%s#createFromUrl";
    protected final SelenideElement urlInput = $("#url");
    protected final SelenideElement proceedButton = $(".saveButtonsBlock .submitButton ");
    protected final SelenideElement buildConfigurationNameInput = $("#buildTypeName");
    protected final SelenideElement connectionSuccessfulMessage = $(".connectionSuccessful");

    protected  CreateBasePage createProject(String url) {
        urlInput.val(url);
        proceedButton.click();
        $(".createFormContainer [class*='progressRingDefault']").shouldNotBe(Condition.visible);
        connectionSuccessfulMessage.shouldBe(Condition.visible, BASE_WAITING);

        return this;
    }
}
