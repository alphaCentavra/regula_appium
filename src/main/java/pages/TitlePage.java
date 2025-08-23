package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.awt.image.BufferedImage;
import java.util.List;

public class TitlePage extends BasePage {
    private static final Logger logger = LogManager.getLogger();

    @AndroidFindBy(id = "com.regula.documentreader:id/loadingPb")
    private WebElement loadingSpinner;
    @AndroidFindBy(id = "android:id/content")
    private WebElement pageContent;
    @AndroidFindBy(id = "com.regula.documentreader:id/fragmentContainer")
    private WebElement pageContainer;
    @AndroidFindBy(id = "com.regula.documentreader:id/logoIv")
    private WebElement pageLogo;
    @AndroidFindBy(id = "com.regula.documentreader:id/scenarioGrid")
    private WebElement scenarioGrid;
    @AndroidFindBy(id = "com.regula.documentreader:id/menuBtn")
    private WebElement menuButton;
    @AndroidFindBy(id = "com.regula.documentreader:id/cameraBtn")
    private WebElement cameraButton;
    @AndroidFindBy(id = "com.regula.documentreader:id/galleryBtn")
    private WebElement galleryButton;

    public TitlePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        waitForElementNotVisible(loadingSpinner);
    }

    @Step("Ожидаем пока отобразится контент страницы")
    public TitlePage waitPageContentDisplayed() {
        logger.info("Ожидаем пока отобразится контент страницы");
        waitForElementVisible(pageContent);
        return this;
    }

    @Step("Проверяем отображается ли логотип станицы")
    public boolean isPageLogoDisplayed() {
        logger.info("Проверяем что отображается логотип станицы");
        return pageLogo.isDisplayed();
    }

    @Step("Проверяем отображается ли кнопка камеры внизу страницы")
    public boolean isCameraButtonDisplayed() {
        logger.info("Проверяем что отображается кнопка камеры внизу страницы");
        return cameraButton.isDisplayed();
    }

    @Step("Проверяем отображается ли кнопка меню внизу страницы")
    public boolean isMenuButtonDisplayed() {
        logger.info("Проверяем что отображается кнопка меню внизу страницы");
        return menuButton.isDisplayed();
    }

    @Step("Проверяем отображается ли кнопка галереи внизу страницы")
    public boolean isGalleryButtonDisplayed() {
        logger.info("Проверяем что отображается кнопка галереи внизу страницы");
        return galleryButton.isDisplayed();
    }

    @Step("Проверяем отображается ли элемент с текстом {text} в таблице со сценариями")
    public boolean isScenarioGridElementDisplayed(String text) {
        logger.info("Проверяем отображается ли элемент с текстом " + text);
        WebElement textField = scrollToElementByText(text);
        return textField.isDisplayed();
    }

    @Step("Проверяем отображаются ли все иконки в таблице со сценариями")
    public boolean checkAllScenarioGridIconsAreDisplayed() {
        boolean returnValue = true;
        List<WebElement> elements = scenarioGrid
                .findElements(By.xpath(".//android.widget.ImageView"));
        for (WebElement element: elements) {
            if (!element.isDisplayed()) {
                returnValue = false;
            }
        }
        return returnValue;
    }

    @Step("Сохраняем скриншот страницы")
    public TitlePage savePageScreenshot(String path, String fileName) {
        logger.info("Сохраняем скриншот страницы");
        saveScreenshot(getPageScreenshot(), path, fileName);
        return this;
    }

    @Step("Получаем скриншот страницы")
    public BufferedImage getPageScreenshot() {
        logger.info("Получаем скриншот страницы");
        return getScreenshot(pageContainer);
    }
}
