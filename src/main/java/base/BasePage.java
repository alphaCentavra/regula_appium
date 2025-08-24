package base;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import config.ConfigReader;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import utils.WaitUtils;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class BasePage {
    private static final Logger logger = LogManager.getLogger();
    protected AppiumDriver driver;
    protected WaitUtils waitUtils;

    protected final int defaultWaitTime = ConfigReader.testDataConfig.defaultWaitTimeInSeconds();
    protected final int customWaitTime = ConfigReader.testDataConfig.customWaitTimeInSeconds();
    protected final String expectedScreensDir = ConfigReader.testDataConfig.folderWithExpectedScreenshots();
    protected final String actualScreensDir = ConfigReader.testDataConfig.folderWithActualScreenshots();
    protected final String comparisonScreensDir = ConfigReader.testDataConfig.folderWithComparisonScreenshots();

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(defaultWaitTime);
        initElements(this);
    }

    @Step("Инициализация PageFactory элементов")
    protected void initElements(Object page) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), page);
    }

    @Step("Ждем до {timeout} секунд пока будет виден элемент")
    protected WebElement $(WebElement element, int timeout) {
        logger.info("Ждем до {} секунд пока будет виден элемент", timeout);
        return waitUtils.waitForElementVisible(element, timeout);
    }

    @Step("Ждем пока будет виден элемент")
    protected WebElement $(WebElement element) {
        return $(element, defaultWaitTime);
    }

    @Step("Ждем пока элемент страницы будет не виден")
    protected void waitForElementNotVisible(WebElement element) {
        waitUtils.waitForElementNotVisible(element);
    }

    @Step("Проверяем видим ли элемент в течение {timeout} секунд")
    protected boolean isElementVisible(WebElement element, int timeout) {
        return waitUtils.isElementVisible(element, timeout);
    }

    @Step("Проверяем видим ли элемент")
    protected boolean isElementVisible(WebElement element) {
        return waitUtils.isElementVisible(element);
    }

    @Step("Cохраняем полученный скриншот в файл с именем {fileName}")
    public void saveScreenshot(BufferedImage bufferedImage, String path, String fileName) {
        logger.info("Cохраняем полученный скриншот в файл");
        File imageFile = new File(path + fileName + ".png");
        try {
            ImageIO.write(bufferedImage, "png", imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Получаем скриншот той области, где расположен проверяемый элемент")
    public BufferedImage getScreenshot(WebElement element) {
        logger.info("Получаем скриншот той области, где расположен проверяемый элемент");
        byte[] srcImage = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        BufferedImage screenshot;
        try {
            screenshot = ImageIO.read(new ByteArrayInputStream(srcImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Point elementLocation = element.getLocation();
        Dimension elementSize = element.getSize();
        return screenshot.getSubimage(elementLocation.x, elementLocation.y,
                elementSize.width, elementSize.height);
    }

    @Step("Сравниваем скриншот '{actualScreenshotName}' со скриншотом '{expectedScreenshotName}' и сохраняем результат"
            + " сравнения в файл с именем {comparisonResultName}")
    public ImageComparisonResult compareScreenshots(String actualScreenshotName,
                                                    String expectedScreenshotName,
                                                    String comparisonResultName) {
        logger.info("Загружаем ожидаемое изображения для сравнения");
        BufferedImage expectedImage = ImageComparisonUtil
                .readImageFromResources(expectedScreensDir + expectedScreenshotName + ".png");
        logger.info("Загружаем актуальный скриншот");
        BufferedImage actualImage = ImageComparisonUtil
                .readImageFromResources(actualScreensDir + actualScreenshotName + ".png");
        logger.info("Сравниваем изображения");
        File comparisonResultImage = new File(comparisonScreensDir + comparisonResultName + ".png");
        return new ImageComparison(expectedImage, actualImage, comparisonResultImage).compareImages();
    }

    @Step("Прокручиваем страницу до элемента с текстом: '{text}'")
    public WebElement scrollToElementByText(String text) {
        logger.info("Прокручиваем страницу до элемента с текстом: {}", text);
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
        ));
    }
}