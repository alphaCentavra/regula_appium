package base;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.TitlePage;
import utils.AppiumDriverUtil;
import utils.CapabilitiesBuilder;

import java.net.URL;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger();
    protected AppiumDriver driver;
    protected TitlePage titlePage;
    protected String platform;

    @BeforeMethod
    public void setUp() {
        //Выполнение необходимых настроек для запуска приложения с мобильного устройства
        platform = ConfigReader.emulatorConfig.platformName();
        DesiredCapabilities capabilities = new CapabilitiesBuilder()
                .setPlatformName(platform)
                .setDeviceName(ConfigReader.emulatorConfig.deviceName())
                .setAutomationName(ConfigReader.emulatorConfig.automationName())
                .setAppPackage(ConfigReader.emulatorConfig.appPackage())
                .setAppActivity(ConfigReader.emulatorConfig.appActivity())
                .setNoReset(ConfigReader.emulatorConfig.noReset())
                .setFullReset(ConfigReader.emulatorConfig.fullReset())
                .build();

        logger.info("Инициализация драйвера");
        try {
            URL remoteUrl = new URL(ConfigReader.emulatorConfig.remoteURLLink());
            driver = AppiumDriverUtil.getDriver(platform, remoteUrl, capabilities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        logger.info("Инициализация объектов страниц приложения");
        titlePage = new TitlePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Закрытие драйвера");
        if (driver != null) {
            driver.quit();
        }
    }
}