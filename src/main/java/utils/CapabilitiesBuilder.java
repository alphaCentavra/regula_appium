package utils;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class CapabilitiesBuilder {
    private final DesiredCapabilities capabilities;

    public CapabilitiesBuilder() {
        this.capabilities = new DesiredCapabilities();
    }

    public CapabilitiesBuilder setPlatformName(String platformName) {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        return this;
    }

    public CapabilitiesBuilder setDeviceName(String deviceName) {
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        return this;
    }

    public CapabilitiesBuilder setApp(String appPath) {
        capabilities.setCapability(MobileCapabilityType.APP, appPath);
        return this;
    }

    public CapabilitiesBuilder setAutomationName(String automationName) {
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        return this;
    }

    public CapabilitiesBuilder setNoReset(Boolean value) {
        capabilities.setCapability(MobileCapabilityType.NO_RESET, value);
        return this;
    }

    public CapabilitiesBuilder setProxy(String proxy) {
        capabilities.setCapability(MobileCapabilityType.PROXY, proxy);
        return this;
    }

    public DesiredCapabilities build() {
        return capabilities;
    }
}
