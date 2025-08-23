package base;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import config.ConfigReader;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BasePage {
    private static final Logger logger = LogManager.getLogger();
    protected AppiumDriver driver;

    private final int waitTimeInSeconds = ConfigReader.testDataConfig.waitTime();
    private final String expectedScreensDir = ConfigReader.testDataConfig.folderWithExpectedScreenshots();
    private final String actualScreensDir = ConfigReader.testDataConfig.folderWithActualScreenshots();
    private final String comparisonScreensDir = ConfigReader.testDataConfig.folderWithComparisonScreenshots();

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
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
            screenshot = ImageIO.read(
                    new ByteArrayInputStream(srcImage));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Point elementLocation = element.getLocation();
            Dimension elementSize = element.getSize();
        return screenshot.getSubimage(elementLocation.x, elementLocation.y,
                elementSize.width, elementSize.height);
    }

    @Step("Сравниваем скриншот '{actualScreenshotName}' со скриншотом '{expectedScreenshotName}' и сохраняем результат сравнения в файл с именем {comparisonResultName}")
    public ImageComparisonResult compareScreenshots(String actualScreenshotName, String expectedScreenshotName, String comparisonResultName) {
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
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"
        ));
    }

    @Step("Ждем {timeout} секунд пока будет виден элемент страницы")
    protected void waitForElementVisible(WebElement element, int timeout) {
        logger.info("Ждем несколько секунд пока будет виден элемент страницы");
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Ждем пока будет виден элемент страницы")
    protected void waitForElementVisible(WebElement element) {
        logger.info("Ждем пока будет виден элемент страницы");
        waitForElementVisible(element, waitTimeInSeconds);
    }

    @Step("Ждем {timeout} секунд пока элемент страницы будет не виден")
    protected void waitForElementInvisible(WebElement element, int timeout) {
        logger.info("Ждем несколько секунд пока элемент страницы будет не виден");
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    @Step("Ждем пока элемент страницы будет не виден")
    protected void waitForElementNotVisible(WebElement element) {
        logger.info("Ждем несколько секунд пока элемент страницы будет не виден");
        waitForElementInvisible(element, waitTimeInSeconds);
    }
}