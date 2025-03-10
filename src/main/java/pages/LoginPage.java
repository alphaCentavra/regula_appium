package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    // использовать логирование
    private static final Logger logger = LogManager.getLogger();

    protected AppiumDriver driver;
    @AndroidFindBy(id = "com.alfabank.qapp:id/etUsername")
    private WebElement loginField;

    @AndroidFindBy(id = "com.alfabank.qapp:id/etPassword")
    private WebElement passwordField;

    @AndroidFindBy(className = "android.widget.Button")
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
        waitForElementVisible(loginField);
        sendKeys(loginField, username);
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
    public String getPasswordFieldAttribute() {
        waitForElementVisible(passwordField);
        return passwordField.getDomAttribute("password");
    }

    public LoginPage waitPageTitleDisplayed() {
        waitForElementVisible(pageTitle);
        return this;
    }

    public boolean isLoginFieldDisplayed() {
        return pageTitle.isDisplayed();
    }

    public String isLoginFieldFocusable() {
        return loginField.getDomAttribute("focusable");
    }

    public String isLoginFieldClickable() {
        return loginField.getDomAttribute("clickable");
    }

    public String getLoginFieldText() {
        return loginField.getText();
    }

    public boolean isPasswordFieldDisplayed() {
        return passwordField.isDisplayed();
    }

    public String isPasswordFieldFocusable() {
        return passwordField.getDomAttribute("focusable");
    }

    public String isPasswordFieldClickable() {
        return passwordField.getDomAttribute("clickable");
    }

    public String isPageTitleFocusable() {
        return pageTitle.getDomAttribute("focusable");
    }

    public boolean isPageTitleDisplayed() {
        return pageTitle.isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public String isLoginButtonFocusable() {
        return loginButton.getDomAttribute("focusable");
    }

    public String isLoginButtonClickable() {
        return loginButton.getDomAttribute("clickable");
    }

    public String getLoginButtonText() {
        waitForElementVisible(showPasswordIcon);
        return loginButton.getText();
    }

    public void waitPageTitleNotVisible() {
        waitForElementNotVisible(pageTitle);
    }

    public String getPageTitle() {
        waitForElementVisible(pageTitle);
        return pageTitle.getText();
    }

    public LoginPage clickShowPasswordIcon() {
        waitForElementVisible(showPasswordIcon);
        click(showPasswordIcon);
        return this;
    }

    public LoginPage clickShowPasswordIcon(int count) {
        waitForElementVisible(showPasswordIcon);
        click(showPasswordIcon, count);
        return this;
    }

    public String getShowPasswordIconCheckedState() {
        waitForElementVisible(showPasswordIcon);
        return showPasswordIcon.getDomAttribute("checked");
    }
}