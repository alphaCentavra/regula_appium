package utils;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesBuilder {
    private final DesiredCapabilities capabilities;

    public CapabilitiesBuilder() {
        this.capabilities = new DesiredCapabilities();
    }

    public CapabilitiesBuilder setPlatformName(String platformName) {
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);
        return this;
    }

    public CapabilitiesBuilder setDeviceName(String deviceName) {
        capabilities.setCapability("deviceName", deviceName);
        return this;
    }

    public CapabilitiesBuilder setAutomationName(String automationName) {
        capabilities.setCapability("automationName", automationName);
        return this;
    }

    public CapabilitiesBuilder setAppPackage(String appPackage) {
        capabilities.setCapability("appPackage", appPackage);
        return this;
    }

    public CapabilitiesBuilder setAppActivity(String appActivity) {
        capabilities.setCapability("appActivity", appActivity);
        return this;
    }

    public CapabilitiesBuilder setNoReset(Boolean value) {
        capabilities.setCapability("noReset", value);
        return this;
    }

    public CapabilitiesBuilder setFullReset(Boolean value) {
        capabilities.setCapability("fullReset", value);
        return this;
    }

    public DesiredCapabilities build() {
        return capabilities;
    }
}
