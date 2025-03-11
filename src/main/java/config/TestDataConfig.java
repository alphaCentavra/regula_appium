package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/configs/testdata.properties"
})
public interface TestDataConfig extends Config {
    @Key("validLogin")
    String validLogin();

    @Key("validPassword")
    String validPassword();

    @Key("loginFieldTextLabel")
    String loginFieldTextLabel();

    @Key("invalidLogin")
    String invalidLogin();

    @Key("invalidPassword")
    String invalidPassword();

    @Key("homePageContent")
    String homePageContent();

    @Key("loginPageTitle")
    String loginPageTitle();

    @Key("inputFieldMaxLength")
    String inputFieldMaxLength();

    @Key("loginButtonText")
    String loginButtonText();

    @Key("passwordFieldTextLabel")
    String passwordFieldTextLabel();

    @Key("waitTime")
    int waitTime();

    @Key("yes")
    String yes();

    @Key("no")
    String no();
}
