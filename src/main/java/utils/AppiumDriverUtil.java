package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

import static data.TestData.waitTime;

public class AppiumDriverUtil {
    private static AppiumDriver driver = null;

    public static AppiumDriver getDriver(URL remoteUrl, DesiredCapabilities capabilities) {
        if (driver == null) {
             int waitInSeconds = Integer.parseInt(waitTime.getValue());
             driver = new AndroidDriver(remoteUrl, capabilities);
             driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitInSeconds));
         }
        return driver;
    }
}
