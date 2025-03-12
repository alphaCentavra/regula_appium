package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    private static final Logger logger = LogManager.getLogger();

    @AndroidFindBy(className = "android.widget.TextView")
    private WebElement textView;

    @AndroidFindBy(id = "com.alfabank.qapp:id/content")
    private WebElement pageContent;

    public HomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Ожидаем пока отобразится содержимое страницы")
    public HomePage waitPageContentVisible() {
        logger.info("Ожидаем пока отобразится содержимое страницы");
        waitForElementVisible(pageContent);
        return this;
    }

    @Step("Получаем содержимое страницы")
    public String getPageContent() {
        logger.info("Получаем содержимое страницы");
        waitForElementVisible(textView);
        return textView.getText();
    }
}
