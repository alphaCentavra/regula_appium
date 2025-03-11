package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {
    public static final EmulatorConfig emulatorConfig = ConfigFactory.create(EmulatorConfig.class, System.getProperties());
    public static final TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class, System.getProperties());
}
