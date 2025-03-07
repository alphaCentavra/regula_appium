package tests;

import base.BaseTest;
import org.testng.annotations.Test;

import static data.TestData.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void testUserSuccessfulLogin() {
        String login = validLogin.getValue();
        String password = validPassword.getValue();
        loginPage
                .enterUsername(login)
                .enterPassword(password)
                .clickLoginButton();
        loginPage.waitPageTitleNotVisible();
        homePage.waitPageContentVisible();
        String actualPageHeader = homePage.getPageContent();
        String expectedPageHeader = "Вход в Alfa-Test выполнен";
        assertEquals(actualPageHeader, expectedPageHeader, "Текст '" + actualPageHeader + "' на домашней странице не равен " + expectedPageHeader);
    }

    @Test //тест дописан до попытки логина с невалидным логином и паролем
    public void testUserUnsuccessfulLogin() {
        String login = invalidLogin.getValue();
        String password = invalidPassword.getValue();
        loginPage
                .enterUsername(login)
                .enterPassword(password)
                .clickLoginButton();
        assertTrue(loginPage.isPageTitleDisplayed(), "Не отображается страница логина");
        // тут должна быть проверка валидационного сообщения поля Логин
//        String usernameValidationMessage = loginPage.getUsernameValidationMessage();;
//        assertEquals(usernameValidationMessage, "InvalidValue");
        // тут должна быть проверка валидационного сообщения поля Пароль
//        String passwordValidationMessage = loginPage.getPasswordValidationMessage();
//        assertEquals(passwordValidationMessage, "ExceptValue");
        //проверяем, что длина пароля была обрезана до допустимой длины
//        int passwordLength = loginPage.getPasswordFieldText().length();
//        assertEquals(passwordLength, inputFieldMaxLength, "Пароль не был обрезан при вставке до допустимой длины");
    }
}
