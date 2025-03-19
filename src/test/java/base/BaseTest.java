package base;

import config.ConfigReader;
import io.appium.java_client.AppiumDriver;;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.LoginPage;
import utils.AppiumDriverUtil;
import mitmproxy.MITMProxy;
import utils.CapabilitiesBuilder;

import java.net.URL;

public class BaseTest {
    private static final Logger logger = LogManager.getLogger();
    protected AppiumDriver driver;
    protected MITMProxy proxy;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected String platform;

    @BeforeMethod
    public void setUp() {
        //Выполнение необходимых настроек для запуска приложения в эмуляторе
        platform = ConfigReader.emulatorConfig.platformName();
        DesiredCapabilities capabilities = new CapabilitiesBuilder()
                .setPlatformName(platform)
                .setDeviceName(ConfigReader.emulatorConfig.deviceName()) // подставить сюда название своего устройства или эмулятора
                .setAutomationName(ConfigReader.emulatorConfig.automationName())
                .setApp(ConfigReader.emulatorConfig.app())// подставить сюда полный путь к .apk файлу
                .setNoReset(ConfigReader.emulatorConfig.noReset())
                .setProxy(ConfigReader.emulatorConfig.proxy())
                .build();

        logger.info("Инициализация драйвера");
        try {
            URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
            driver = AppiumDriverUtil.getDriver(platform, remoteUrl, capabilities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        logger.info("Инициализация прослушивания трафика");
        proxy = MITMProxy.getProxy();

        logger.info("Инициализация объектов страниц приложения");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        MITMProxy.getProxy().stopProxyListener();
        logger.info("Закрытие драйвера");
        if (driver != null) {
            driver.quit();
        }
    }
}