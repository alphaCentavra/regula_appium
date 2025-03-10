package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    // использовать логирование
    private static final Logger logger = LogManager.getLogger();

    protected AppiumDriver driver;

    @AndroidFindBy(className = "android.widget.TextView")
    private WebElement textView;

    @AndroidFindBy(id = "com.alfabank.qapp:id/content")
    private WebElement pageContent;

    public HomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public HomePage waitPageContentVisible() {
        waitForElementVisible(pageContent);
        return this;
    }

    public String getPageContent() {
        waitForElementVisible(textView);
        return textView.getText();
    }
}
