package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger();
    protected AppiumDriver driver;
    protected LoginPage loginPage;
    protected HomePage homePage;

    @BeforeMethod
    public void setUp() {
        // Выполняем необходимые настройки для запуска приложения в эмуляторе
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Medium Phone API 35");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.APP, "C:/temp/idea_projects/appium/src/main/resources/apk/app.apk"); // подставить сюда полный путь к .apk файлу
//        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.alfabank.qapp"); // предварительно необходимо установить приложение на эмулятор
//        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, driver.currentActivity());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        // Инициализируем драйвер
        try {
            int waitInSeconds = 10;
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitInSeconds));
        } catch (MalformedURLException e) {
            logger.error(e.getMessage());
        }

        // Инициализируем объекты страниц приложения
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}