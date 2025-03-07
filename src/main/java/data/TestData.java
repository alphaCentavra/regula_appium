package data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestData {
    validLogin("Login"),
    validPassword("Password"),
    invalidLogin("Login1"),
    invalidPassword("123456789012345678901234567890123456789012345678901"),
    inputFieldMaxLength("50");

    private final String value;
}
