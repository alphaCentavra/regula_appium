package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static data.TestData.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


/*
Подробное описание дефектов находится в файле resources -> AppDefects.docx
Список тест кейсов находится в файле resources -> TestCases.docx
*/

public class LoginPositiveTest extends BaseTest {

    @Test
    @AllureId("1")
    @Description("Проверяем, что основные элементы страницы отображены на странице и имеют необходимые характеристики")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("Дефект №3 - Кнопка входа имеет неверное название")
    public void testUserMainLoginPageElements() {
        // тестовые данные
        String actualLoginPageTitle = "";
        String expectedLoginPageTitle = loginPageTitle.getValue();
        String trueValue = yes.getValue();
        String falseValue = no.getValue();
        String loginFieldText = loginFieldTextLabel.getValue();
        String passwordFieldText = passwordFieldTextLabel.getValue();
        String loginButtonName = loginButtonText.getValue();

        // ШАГ 1 - проверяем видимость и недоступность заголовка, а также его отображаемый текст
        loginPage.waitPageTitleDisplayed();
        assertEquals(loginPage.isPageTitleFocusable(), falseValue, "Заголовку страницы можно установить фокус");
        actualLoginPageTitle = loginPage.getPageTitle();
        assertEquals(actualLoginPageTitle, expectedLoginPageTitle,
                "Заголовок '" + actualLoginPageTitle + "' на странице логина не равен " + expectedLoginPageTitle);

        // ШАГ 2 - проверяем видимость и доступность поля 'Логин', то что это текстовое поле, которое имеет лейбл "Логин"
        assertTrue(loginPage.isLoginFieldDisplayed(), "Поле 'Логин' не отображается на странице авторизации");
        assertEquals(loginPage.isLoginFieldFocusable(), trueValue, "В поле 'Логин' страницы авторизации нельзя установить фокус");
        assertEquals(loginPage.isLoginFieldClickable(), trueValue, "Поле 'Логин' страницы авторизации не является кликабельным");
        assertEquals(loginPage.getLoginFieldText(), loginFieldText, "Текст поля 'Логин' страницы авторизации не равен '" + loginFieldText + "'");

        // ШАГ 3 - проверяем видимость и доступность поля 'Пароль', то что это текстовое поле, которое имеет лейбл "Пароль"
        assertTrue(loginPage.isPasswordFieldDisplayed(), "Поле 'Пароль' не отображается на странице авторизации");
        assertEquals(loginPage.isPasswordFieldFocusable(), trueValue, "В поле 'Пароль' страницы авторизации нельзя установить фокус");
        assertEquals(loginPage.isPasswordFieldClickable(), trueValue, "Поле 'Пароль' страницы авторизации не является кликабельным");
        assertEquals(loginPage.getPasswordFieldText(), passwordFieldText, "Текст поля 'Пароль' страницы авторизации не равен '" + passwordFieldText + "'");

        // ШАГ 4 - проверяем видимость и доступность кнопки 'Войти' и её название
        assertTrue(loginPage.isLoginButtonDisplayed(), "Кнопка 'Войти' не отображается на странице авторизации");
        assertEquals(loginPage.isLoginButtonFocusable(), trueValue, "На кнопку 'Войти' страницы авторизации нельзя установить фокус");
        assertEquals(loginPage.isLoginButtonClickable(), trueValue, "Кнопка 'Войти' страницы авторизации не кликабельна");
        assertEquals(loginPage.getLoginButtonText(), loginButtonName, "Название кнопки логина не равно '" + loginButtonName + "'");
    }

    @Test //тест дописан до попытки логина с невалидным логином и паролем
    @AllureId("2")
    @Description("Проверяем, что основные элементы страницы отображены на странице и имеют необходимые характеристики")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("Дефект №1 - Не отображается валидационное сообщение поля 'Логин'")
    @Issue("Дефект №2 - Не отображается валидационное сообщение поля 'Пароль'")
    public void testUserLogin() {
        // тестовые данные
        String validLoginValue = validLogin.getValue();
        String validPasswordValue = validPassword.getValue();
        String invalidLoginValue = invalidLogin.getValue();
        String invalidPasswordValue = invalidPassword.getValue();
        String expectedHomePageContent = homePageContent.getValue();
        String actualPageContent = "";

        // ШАГ 1 - проверяем, что пользователь не может залогиниться с невалидными логином и паролем
        loginPage
                .enterUsername(invalidLoginValue)
                .enterPassword(invalidPasswordValue)
                .clickLoginButton();
        assertTrue(loginPage.isPageTitleDisplayed(), "Не отображается заголовок страницы логина");
        // тут должны быть проверки валидационных сообщений полей Логин и Пароль
//        List<InterceptedMessages> networkLogMessages = proxy.getNetworkCalls();
        // ШАГ 2 - проверяем, что пользователь может залогиниться с валидным логином и паролем
        loginPage
                .enterUsername(validLoginValue)
                .enterPassword(validPasswordValue)
                .clickLoginButton()
                .waitPageTitleNotVisible(); // тут еще нужно будет сделать перехват трафика и убедиться, что вызывается API метод authorize
        // проверяем, что в списке отправленных сетевых запросов есть метод 'authorize'
//        for (InterceptedMessages msg : networkLogMessages) {
//            System.out.println("Captured Request at: " + msg.getTimestamp());
//            System.out.println("Request Details: " + msg.getInterceptedMessage());
//        }
        homePage.waitPageContentVisible();
        actualPageContent = homePage.getPageContent();
        assertEquals(homePage.getPageContent(), expectedHomePageContent,
                "Текст '" + actualPageContent + "' на домашней странице не равен " + expectedHomePageContent);
    }

    @Test
    @AllureId("3")
    @Description("Проверяем, что пароль может быть как скрыт, так и отображен")
    @Severity(SeverityLevel.CRITICAL)
    @Issue("Дефект №4 - Иконка показа.скрытия пароля имеет неверную анимацию")
    public void testPasswordCouldBeHiddenAndShown() { // если получится, можно здесь запилить проверку скриншотов
        // тестовые данные
        String passwordFieldText = validPassword.getValue();
        String trueValue = yes.getValue();
        String falseValue = no.getValue();

        // ШАГ 1 - вводим текст в поле для ввода пароля
        loginPage
                .waitPageTitleDisplayed()
                .enterPassword(passwordFieldText);
        //проверить скриншот, пароль не показывается и что иконка checked т.е. "глазик" закрыт
        assertEquals(loginPage.getPasswordFieldAttribute(), trueValue, "Поле 'Пароль' не предназначено для ввода пароля");
        assertEquals(loginPage.getShowPasswordIconCheckedState(), trueValue, "По умолчанию иконка должна отображаться в перечеркнутом виде");
        loginPage
                .clickShowPasswordIcon(1);
        //проверить скриншот, что пароль показывается и что иконка не checked т.е. "глазик" открыт
        assertEquals(loginPage.getShowPasswordIconCheckedState(), falseValue, "При одиночном клике иконка должна менять свое состояние на противоположное");
        loginPage
                .clickShowPasswordIcon(2);
        //проверить скриншот, что пароль по-прежнему показывается и что иконка все еще не checked
        assertEquals(loginPage.getShowPasswordIconCheckedState(), falseValue, "При двойном клике иконка не должна менять свое состояние");
    }
}
