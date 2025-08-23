package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "file:src/test/resources/configs/emulator.properties"
})
public interface EmulatorConfig extends Config {

    @Key("deviceName")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("automationName")
    String automationName();

    @Key("appPackage")
    String appPackage();

    @Key("appActivity")
    String appActivity();

    @Key("noReset")
    @DefaultValue("true")
    boolean noReset();

    @Key("fullReset")
    @DefaultValue("false")
    boolean fullReset();

    @Key("remoteURLLink")
    String remoteURLLink();
}
