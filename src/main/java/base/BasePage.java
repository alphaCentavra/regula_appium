package base;

;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected AppiumDriver driver;
    private final int waitTimeInSeconds = 10;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected void waitForElementVisible(WebElement element, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementVisible(WebElement element) {
        waitForElementVisible(element, waitTimeInSeconds);
    }

    protected void waitForElementInvisible(WebElement element, int timeout) {
        new WebDriverWait(driver, Duration.ofSeconds(timeout))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitForElementNotVisible(WebElement element) {
        waitForElementInvisible(element, waitTimeInSeconds);
    }
}