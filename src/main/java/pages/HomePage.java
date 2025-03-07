package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    protected AppiumDriver driver;
    private  int waitTimeInSeconds = 30; // можно убрать в пропертис

    @AndroidFindBy(className = "android.widget.TextView")
    private WebElement textView;

    @AndroidFindBy(id = "com.alfabank.qapp:id/content")
    private WebElement pageContent;

    public HomePage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public HomePage waitPageContentVisible() {
        waitForElementVisible(pageContent, waitTimeInSeconds);
        return this;
    }

    public String getPageContent() {
        waitForElementVisible(textView, waitTimeInSeconds);
        return textView.getText();
    }
}
