package ilya.lesnikov.ui.tests;

import ilya.lesnikov.api.enums.Endpoint;
import ilya.lesnikov.ui.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

@Tag("regress")
@DisplayName("Проверки компонента создания проекта")
public class CreateProjectTests extends BaseUiTest {
    @Test
    @Tags({@Tag("positive"), @Tag("smoke")})
    @DisplayName("Создание проекта пользователем")
    public void userCreateProjectTest() {
        step("Авторизация пользователя", () -> {
            superUserCheckedRequest.getRequest(Endpoint.USERS).create(testData.getUser());
            LoginPage.open().login(testData.getUser());
        });
        step("Открытие веб-формы создания проекта", () -> {});
        step("Отправка заполненной веб-формы создания проекта", () -> {});
        step("Нажатие кнопки ''", () -> {});
        step("Проверка", () -> {});
    }
}
