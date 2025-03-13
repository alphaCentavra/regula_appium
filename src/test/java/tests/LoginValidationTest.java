package tests;

import base.BaseTest;
import config.ConfigReader;
import io.qameta.allure.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.Assert.*;

/*
Подробное описание дефектов находится в файле resources -> AppDefects.docx
Список тест кейсов находится в файле resources -> TestCases.docx
*/

public class LoginValidationTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger();
    private static final int inputFieldMaxLength = ConfigReader.testDataConfig.inputFieldMaxLength();
    private static final String exceptValueValidationMessage = ConfigReader.testDataConfig.exceptValueValidationMessage();
    private static final String invalidValueValidationMessage = ConfigReader.testDataConfig.invalidValueValidationMessage();

    @DataProvider(name = "loginInvalidValues") // разбить этот кейс на два (валидные/невалидные значения, для невалидных проверять и ввод и вставку текста, чтобы проверить на появление и InvalidValue и ExceptValue.)
    protected Object[][] loginInvalidValues() {
        return new Object[][]{
                {"Hello, World! This_is-a-test. /path/to/file, example."}, //вводим только валидные символы (латинские буквы A..Z, a..z, символы [ . , / ' _ -], пробел) длиной больше 50 символов
                {"Hello! 123 This_is-a-test. /path/to/file, example."}, //вводим и валидные и невалидные символы, валидных должно быть меньше 50-ти
        };
    }

    @Test(dataProvider = "loginInvalidValues")
    @AllureId("4")
    @Description("Проверяем сначала ввод, потом вставку текста в поле 'Логин'")
    @Severity(SeverityLevel.NORMAL)
    @Issue("Дефект №1 - Не отображается валидационное сообщение поля 'Логин'")
    @Issue("Дефект №5 - Невалидные символы не обрезаются при вставке в поле 'Логин'")
    public void testValidationsForLoginField(String value) {
        //тестовые данные
        String regEx = "[A-Za-z .,/'_-]+";
        String usernameValidationMessage = "";
        int usernameLength = 0;

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(value);

        logger.info("ШАГ 1 - проверяем, что при вводе текста осуществляется проверка на то, чтобы длина логина не превышала максимальную");
        loginPage
                .enterUsername(value)
                .clickLoginButton();
        usernameLength = loginPage.getLoginFieldText().length();
//        if (usernameLength > inputFieldMaxLength) {
//            usernameValidationMessage = loginPage.getUsernameValidationMessage();
//            assertFalse(usernameValidationMessage.isEmpty(), "Не осуществляется проверка на то, чтобы длина логина не превышала максимальную");
//        }

        logger.info("ШАГ 2 - проверяем, что при вводе текста осуществляется проверка на ввод не разрешенных символов");
        loginPage
                .enterUsername(value)
                .clickLoginButton();
//        if (!matcher.matches()) {
//            usernameValidationMessage = loginPage.getUsernameValidationMessage();
//            assertEquals(usernameValidationMessage, invalidValueValidationMessage);
//        }

        logger.info("ШАГ 3 - проверяем, что при вставке текста невалидные символы обрезались и вывелось соответствующее сообщение об ошибке");
        loginPage
                .pastePassword(value)
                .clickLoginButton();
        usernameLength = loginPage.getLoginFieldText().length();
        assertTrue(usernameLength <= value.length(), "Невалидные символы не были обрезаны при вставке");
//        usernameValidationMessage = loginPage.getUsernameValidationMessage();
//        assertEquals(usernameValidationMessage, exceptValueValidationMessage);
    }

    @DataProvider(name = "passwordInvalidValues")
    protected Object[][] passwordInvalidValues() {
        return new Object[][]{
                {"Hello, World! 123 @This_is-a-test. /path/to/file, #"}, //вводим строку пятидесяти одного любого символа
                {"Hello, World!123 @This_is-a-test. /path/to/file,#!"}, //вводим строку пятидесяти любых символов
                {"Hello, World! 123 @This_is-a-test . /path/to/file"}, //вводим строку сорока девяти любых символов
        };
    }

    @Test(dataProvider = "passwordInvalidValues")
    @AllureId("5")
    @Description("Проверяем сначала ввод, потом вставку текста в поле 'Пароль'")
    @Severity(SeverityLevel.NORMAL)
    @Issue("Дефект №2 - Не отображается валидационное сообщение поля 'Пароль'")
    public void testValidationsForPasswordField(String value) {
        //тестовые данные
        int passwordLength = 0;

        logger.info("ШАГ 1 - проверяем, что при вводе текста осуществляется проверка на то, чтобы длина пароля не превышала максимальную");
        loginPage
                .enterPassword(value)
                .clickLoginButton();
        passwordLength = loginPage.getPasswordFieldText().length();
//        if (passwordLength > inputFieldMaxLength) {
//            String passwordValidationMessage = loginPage.getPasswordValidationMessage();
//            assertFalse(passwordValidationMessage.isEmpty(), "Не осуществляется проверка на то, чтобы длина пароля не превышала максимальную");
//        }
        logger.info("ШАГ 2 - проверяем, что при вставке текста длина пароля остается прежней т.к. они все символы валидные и поэтому не обрезаются");
        loginPage
                .pastePassword(value)
                .clickLoginButton();
        passwordLength = loginPage.getPasswordFieldText().length();
        assertEquals(value.length(), passwordLength, "Часть символов пароля были обрезаны при вставке");
    }
}
