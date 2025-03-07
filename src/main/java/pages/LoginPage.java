package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    protected AppiumDriver driver;
    @AndroidFindBy(id = "com.alfabank.qapp:id/etUsername")
    private WebElement usernameField;

    @AndroidFindBy(id = "com.alfabank.qapp:id/etPassword")
    private WebElement passwordField;

    @AndroidFindBy(id = "com.alfabank.qapp:id/btnConfirm")
    private WebElement loginButton;

    @AndroidFindBy(id = "com.alfabank.qapp:id/tvTitle")
    private WebElement pageTitle;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Show password\"]")
    private WebElement showPasswordIcon;

    @AndroidFindBy(xpath = "username.validation.field.message.locator") // подставить реальное значение локатора, когда он будет существовать
    private WebElement usernameValidationMessage;

    @AndroidFindBy(xpath = "password.validation.message.field.locator") // подставить реальное значение локатора, когда он будет существовать
    private WebElement passwordValidationMessage;

    public LoginPage(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public LoginPage enterUsername(String username) {
        waitForElementVisible(usernameField);
        sendKeys(usernameField, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        waitForElementVisible(passwordField);
        sendKeys(passwordField, password);
        return this;
    }

    public String getUsernameValidationMessage() {
        waitForElementVisible(usernameValidationMessage);
        return usernameValidationMessage.getText();
    }

    public String getPasswordValidationMessage() {
        waitForElementVisible(passwordValidationMessage);
        return passwordValidationMessage.getText();
    }

    public LoginPage clickLoginButton() {
        waitForElementVisible(loginButton);
        click(loginButton);
        return this;
    }

    public String getPasswordFieldText() {
        waitForElementVisible(passwordField);
        return passwordField.getText();
    }

    public boolean isPageTitleDisplayed() {
        return pageTitle.isDisplayed();
    }

    public void waitPageTitleNotVisible() {
        waitForElementNotVisible(pageTitle);
    }

    public LoginPage clickShowPasswordIcon() {
        waitForElementVisible(showPasswordIcon);
        click(loginButton);
        return this;
    }
}