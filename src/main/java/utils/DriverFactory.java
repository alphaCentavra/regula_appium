package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class DriverFactory {

    public static AppiumDriver createDriver(String platform, DesiredCapabilities capabilities, URL appiumServerUrl) throws Exception {
        switch (platform.toLowerCase()) {
            case "android":
                return new AndroidDriver(appiumServerUrl, capabilities);
            case "ios":
                return new IOSDriver(appiumServerUrl, capabilities);
            default:
                throw new IllegalArgumentException("Unsupported platform: " + platform);
        }
    }
}
