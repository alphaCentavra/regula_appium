package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
        waitForElementNotVisible(loadingSpinner);
        waitPageContentDisplayed();
    }

    @Step("Ожидаем пока отобразится контент страницы")
    public TitlePage waitPageContentDisplayed() {
        logger.info("Ожидаем пока отобразится контент страницы");
        $(pageContent);
        return this;
    }

    @Step("Проверяем отображается ли логотип страницы")
    public boolean isPageLogoDisplayed() {
        logger.info("Проверяем что отображается логотип страницы");
        return isElementVisible(pageLogo);
    }

    @Step("Проверяем отображается ли кнопка камеры внизу страницы")
    public boolean isCameraButtonDisplayed() {
        logger.info("Проверяем что отображается кнопка камеры внизу страницы");
        return isElementVisible(cameraButton);
    }

    @Step("Проверяем отображается ли кнопка меню внизу страницы")
    public boolean isMenuButtonDisplayed() {
        logger.info("Проверяем что отображается кнопка меню внизу страницы");
        return isElementVisible(menuButton);
    }

    @Step("Проверяем отображается ли кнопка галереи внизу страницы")
    public boolean isGalleryButtonDisplayed() {
        logger.info("Проверяем что отображается кнопка галереи внизу страницы");
        return isElementVisible(galleryButton);
    }

    @Step("Проверяем отображается ли элемент с текстом {text} в таблице со сценариями")
    public boolean isScenarioGridElementDisplayed(String text) {
        logger.info("Проверяем отображается ли элемент с текстом " + text);
        WebElement textField = scrollToElementByText(text);
        return isElementVisible(textField);
    }

    @Step("Проверяем отображаются ли все иконки в таблице со сценариями")
    public boolean areAllScenarioGridIconsDisplayed() {
        logger.info("Проверяем отображаются ли все иконки в таблице со сценариями");
        $(scenarioGrid); // ждем сначала появления самого grid
        List<WebElement> iconElements = scenarioGrid.findElements(By.xpath(".//android.widget.ImageView"));
        if (iconElements.isEmpty()) {
            logger.warn("Не найдено иконок в scenario grid");
            return false;
        }
        for (WebElement icon : iconElements) {
            if (!isElementVisible(icon, customWaitTime)) {
                logger.warn("Иконка не отображается: {}", icon);
                return false;
            }
        }
        return true;
    }

    @Step("Сохраняем скриншот содержимого заглавной страницы")
    public TitlePage saveContentPageScreenshot(String path, String fileName) {
        logger.info("Сохраняем скриншот содержимого заглавной страницы");
        saveScreenshot(getPageScreenshot(pageContainer), path, fileName);
        return this;
    }

    @Step("Сохраняем скриншот логотипа заглавной страницы")
    public TitlePage saveLogoPageScreenshot(String path, String fileName) {
        logger.info("Сохраняем скриншот логотипа заглавной страницы");
        saveScreenshot(getPageScreenshot(pageLogo), path, fileName);
        return this;
    }

    @Step("Получаем скриншот элемента страницы")
    public BufferedImage getPageScreenshot(WebElement element) {
        logger.info("Получаем скриншот элемента страницы");
        return getScreenshot(element);
    }
}