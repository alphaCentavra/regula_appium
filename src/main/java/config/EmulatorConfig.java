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

    @Key("app")
    String app();

    @Key("noReset")
    @DefaultValue("true")
    boolean noReset();

    @Key("proxy")
    String proxy();

    @Key("remoteURLLink")
    String remoteURLLink();
}
