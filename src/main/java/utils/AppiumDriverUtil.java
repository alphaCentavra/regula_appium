package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

import static data.TestData.waitTime;

public class AppiumDriverUtil {
    private static AppiumDriver driver = null;

    public static AppiumDriver getDriver(String platform, URL remoteUrl, DesiredCapabilities capabilities) throws Exception {
//        if (driver == null) {
             int waitInSeconds = Integer.parseInt(waitTime.getValue());
            driver = DriverFactory.createDriver(platform, capabilities, remoteUrl);
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitInSeconds));
//         }
        return driver;
    }
}
