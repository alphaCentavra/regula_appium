package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/configs/testdata.properties"
})
public interface TestDataConfig extends Config {

    @Key("folderWithExpectedScreenshots")
    String folderWithExpectedScreenshots();

    @Key("folderWithActualScreenshots")
    String folderWithActualScreenshots();

    @Key("folderWithComparisonScreenshots")
    String folderWithComparisonScreenshots();

    @Key("waitTime")
    int waitTime();

    @Key("expectedValue")
    String expectedValue();

    @Key("actualValue")
    String actualValue();

    @Key("imageComparisonResultValue")
    String imageComparisonResultValue();
}
