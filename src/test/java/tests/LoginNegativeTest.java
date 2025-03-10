package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
Подробное описание дефектов находится в файле resources -> AppDefects.docx
Список тест кейсов находится в файле resources -> TestCases.docx
*/

public class LoginNegativeTest extends BaseTest {

    // добавить аллюр, прикрутить запуск из .xml файла
    @DataProvider(name = "loginInvalidValues") // разбить этот кейс на два (валидные/невалидные значения, для невалидных проверять и ввод и вставку текста, чтобы проверить на появление и InvalidValue и ExceptValue.)
    protected Object[][] loginInvalidValues() {
        return new Object[][]{
                {""}, //сюда вводим только валидные символы (латинские буквы A..Z, a..z, символы [ . , / ' _ -], пробел) длиной больше 50 символов (51)
                {""}, //сюда вводим и валидные и невалидные символы, валидных должно быть меньше 50-ти (49)
        };
    }

    @Test(dataProvider = "loginInvalidValues") // Это негативный тест. Проверяем сначала ввод, потом вставку текста. Используем регулярку, чтобы получить то сообщение, в котором обрезаются невалидные символы.
    @Severity(SeverityLevel.NORMAL)
    @AllureId("5")
    @Description("Проверяем сначала ввод, потом вставку текста в поле 'Логин'")
    public void testValidationsForLoginField() {

    }

    @DataProvider(name = "passwordInvalidValues") // разбить этот кейс на два (валидные/невалидные значения, для невалидных проверять и ввод и вставку текста, чтобы проверить на появление и InvalidValue и ExceptValue.)
    protected Object[][] passwordInvalidValues() {
        return new Object[][]{
                {""}, //сюда вводим только валидные символы, длиной больше 50
                {""}, //сюда вводим и валидные и невалидные символы, валидных должно быть больше 50-ти (51)
                {""}, //сюда вводим только невалидные символы любой длины
        }; // еще можно вводить пустую строку и строку из только невалидных символов любой длины
    }

    @Test(dataProvider = "passwordInvalidValues")// Это негативный тест. Проверяем сначала ввод, потом вставку текста. Используем регулярку, чтобы получить то сообщение, в котором обрезаются невалидные символы.
    @AllureId("4")
    @Description("Проверяем сначала ввод, потом вставку текста в поле 'Пароль'")
    @Severity(SeverityLevel.NORMAL)
    public void testValidationsForPasswordField() {

    }

// подумать куда это перенести
//        String usernameValidationMessage = loginPage.getUsernameValidationMessage();;
//        assertEquals(usernameValidationMessage, "InvalidValue");
    // тут должна быть проверка валидационного сообщения поля Пароль
//        String passwordValidationMessage = loginPage.getPasswordValidationMessage();
//        assertEquals(passwordValidationMessage, "ExceptValue");
    //проверяем, что длина пароля была обрезана до допустимой длины
//        int passwordLength = loginPage.getPasswordFieldText().length();
//        assertEquals(passwordLength, inputFieldMaxLength, "Пароль не был обрезан при вставке до допустимой длины");
}
