import base.BaseTest;
import io.appium.java_client.AppiumBy;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPageTest extends BaseTest {
    private final Logger logger = LogManager.getLogger(LoginPageTest.class);

    @Test
    public void testAppLaunch() {
        // Verify the app launches successfully
        String appName = driver.findElement(AppiumBy.id("com.example.app:id/appName")).getText();
        Assert.assertEquals(appName, "My App", "App name does not match!");
    }

    @Test
    public void testLogin() {
        // Perform login and verify
        driver.findElement(AppiumBy.id("com.example.app:id/username")).sendKeys("testuser");
        driver.findElement(AppiumBy.id("com.example.app:id/password")).sendKeys("password");
        driver.findElement(AppiumBy.id("com.example.app:id/loginButton")).click();

        String welcomeMessage = driver.findElement(AppiumBy.id("com.example.app:id/welcomeMessage")).getText();
        Assert.assertTrue(welcomeMessage.contains("Welcome"), "Login failed!");
    }
}
