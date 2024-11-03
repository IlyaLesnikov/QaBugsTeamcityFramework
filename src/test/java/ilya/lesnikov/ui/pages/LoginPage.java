package ilya.lesnikov.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ilya.lesnikov.api.models.User;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage {
    private static final String LOGIN_ENDPOINT = "/login.html";
    private final SelenideElement inputUsername = $("#username");
    private final SelenideElement inputPassword = $("#password");
    private final SelenideElement buttonLogin = $(".loginButton");

    public static LoginPage open() {
        return  Selenide.open(LOGIN_ENDPOINT, LoginPage.class);
    }

    public ProjectsPage login(User user) {
        inputUsername.val(user.getUsername());
        inputPassword.val(user.getPassword());
        buttonLogin.click();

        return Selenide.page(ProjectsPage.class);
    }
}
