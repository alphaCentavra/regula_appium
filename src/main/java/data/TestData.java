package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
//переделать в пропертис файл

public enum TestData {
    validLogin("Login"),
    validPassword("Password"),
    invalidLogin("Login1"),
    loginFieldTextLabel("Логин"),
    passwordFieldTextLabel("Пароль"),
    loginButtonText("Войти"),
    invalidPassword("123456789012345678901234567890123456789012345678901"),
    homePageContent("Вход в Alfa-Test выполнен"),
    loginPageTitle("Вход в Alfa-Test"),

    inputFieldMaxLength("50"),
    waitTime("5"),
    yes("true"),
    no("false");

    private final String value;
}
