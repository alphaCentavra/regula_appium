package utils;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class AppiumDriverUtil {
    private static AppiumDriver driver = null;

    public static AppiumDriver getDriver(String platform, URL remoteUrl, DesiredCapabilities capabilities) throws Exception {
        int waitInSeconds = ConfigReader.testDataConfig.defaultWaitTimeInSeconds();
        driver = DriverFactory.createDriver(platform, capabilities, remoteUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitInSeconds));
        return driver;
    }
}
