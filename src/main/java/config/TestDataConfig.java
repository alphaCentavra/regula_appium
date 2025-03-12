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

    @Key("invalidLogin")
    String invalidLogin();

    @Key("invalidPassword")
    String invalidPassword();

    @Key("inputFieldMaxLength")
    String inputFieldMaxLength();

    @Key("folderWithExpectedScreenshots")
    String folderWithExpectedScreenshots();

    @Key("folderWithActualScreenshots")
    String folderWithActualScreenshots();

    @Key("folderWithComparisonScreenshots")
    String folderWithComparisonScreenshots();

    @Key("passwordNotShownExpectedValue")
    String passwordNotShownExpectedValue();

    @Key("passwordNotShownActualValue")
    String passwordNotShownActualValue();

    @Key("imageComparisonResultNotShownValue")
    String imageComparisonResultNotShownValue();

    @Key("passwordIsShownExpectedValue")
    String passwordIsShownExpectedValue();

    @Key("passwordIsShownActualValue")
    String passwordIsShownActualValue();

    @Key("imageComparisonResultIsShownValue")
    String imageComparisonResultIsShownValue();

    @Key("waitTime")
    int waitTime();

    @Key("yes")
    String yes();

    @Key("no")
    String no();
}
