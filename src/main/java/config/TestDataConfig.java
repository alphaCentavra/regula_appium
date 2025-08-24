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

    @Key("defaultWaitTimeInSeconds")
    int defaultWaitTimeInSeconds();

    @Key("customWaitTimeInSeconds")
    int customWaitTimeInSeconds();

    @Key("expectedContentValue")
    String expectedContentValue();

    @Key("actualContentValue")
    String actualContentValue();

    @Key("contentComparisonResultValue")
    String contentComparisonResultValue();

    @Key("expectedLogoValue")
    String expectedLogoValue();

    @Key("actualLogoValue")
    String actualLogoValue();

    @Key("logoComparisonResultValue")
    String logoComparisonResultValue();
}
