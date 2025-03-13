package pages;

import base.BasePage;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class LoginPage extends BasePage {

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

    @Step("Вводим логин в поле ввода логина")
    public LoginPage enterUsername(String username) {
        logger.info("Вводим логин в поле ввода логина");
        waitForElementVisible(loginField);
        sendKeys(loginField, username);
        return this;
    }

    @Step("Вводим пароль в поле ввода пароля")
    public LoginPage enterPassword(String password) {
        logger.info("Вводим логин в поле ввода пароля");
        waitForElementVisible(passwordField);
        sendKeys(passwordField, password);
        return this;
    }

    @Step("Вставляем пароль в поле ввода пароля")
    public LoginPage pastePassword(String password) {
        logger.info("Вставляем логин в поле ввода пароля");
        waitForElementVisible(passwordField);
        pasteSymbols(passwordField, password);
        return this;
    }

    @Step("Получаем сообщение о валидации поля ввода логина")
    public String getUsernameValidationMessage() {
        logger.info("Получаем сообщение о валидации поля ввода логина");
        waitForElementVisible(usernameValidationMessage);
        return usernameValidationMessage.getText();
    }

    @Step("Получаем сообщение о валидации поля ввода пароля")
    public String getPasswordValidationMessage() {
        logger.info("Получаем сообщение о валидации поля ввода пароля");
        waitForElementVisible(passwordValidationMessage);
        return passwordValidationMessage.getText();
    }

    @Step("Кликаем по полю ввода логина")
    public LoginPage clickLoginButton() {
        logger.info("Кликаем по полю ввода логина");
        waitForElementVisible(loginButton);
        click(loginButton);
        return this;
    }

    @Step("Получаем текст поля ввода пароля")
    public String getPasswordFieldText() {
        logger.info("Получаем текст поля ввода пароля");
        waitForElementVisible(passwordField);
        return passwordField.getText();
    }
    @Step("Получаем данные о поле ввода пароля")
    public String getPasswordFieldAttribute() {
        logger.info("Получаем данные о поле ввода пароля");
        waitForElementVisible(passwordField);
        return passwordField.getDomAttribute("password");
    }

    @Step("Ожидаем пока отобразится заголовок страницы")
    public LoginPage waitPageTitleDisplayed() {
        logger.info("Ожидаем пока отобразится заголовок страницы");
        waitForElementVisible(pageTitle);
        return this;
    }

    @Step("Проверяем отображается ли поле ввода логина")
    public boolean isLoginFieldDisplayed() {
        logger.info("Проверяем отображается ли поле ввода логина");
        return pageTitle.isDisplayed();
    }

    @Step("Проверяем можно ли поставить фокус в поле ввода логина")
    public String isLoginFieldFocusable() {
        logger.info("Проверяем можно ли поставить фокус в поле ввода логина");
        return loginField.getDomAttribute("focusable");
    }

    @Step("Проверяем является ли кликабельным поле ввода логина")
    public String isLoginFieldClickable() {
        logger.info("Проверяем является ли кликабельным поле ввода логина");
        return loginField.getDomAttribute("clickable");
    }

    @Step("Получаем текст поля ввода логина")
    public String getLoginFieldText() {
        logger.info("Получаем текст поля ввода логина");
        return loginField.getText();
    }

    @Step("Проверяем отображается ли поле ввода пароля")
    public boolean isPasswordFieldDisplayed() {
        logger.info("Проверяем отображается ли поле ввода пароля");
        return passwordField.isDisplayed();
    }

    @Step("Проверяем можно ли поставить фокус в поле ввода пароля")
    public String isPasswordFieldFocusable() {
        logger.info("Проверяем можно ли поставить фокус в поле ввода пароля");
        return passwordField.getDomAttribute("focusable");
    }

    @Step("Проверяем кликабельно ли поле ввода пароля")
    public String isPasswordFieldClickable() {
        logger.info("Проверяем кликабельно ли поле ввода пароля");
        return passwordField.getDomAttribute("clickable");
    }

    @Step("Проверяем можно ли поставить фокус на заголовок страницы")
    public String isPageTitleFocusable() {
        logger.info("Проверяем можно ли поставить фокус на заголовок страницы");
        return pageTitle.getDomAttribute("focusable");
    }

    @Step("Проверяем виден ли заголовок страницы")
    public boolean isPageTitleDisplayed() {
        logger.info("Проверяем виден ли заголовок страницы");
        return pageTitle.isDisplayed();
    }

    @Step("Проверяем видна ли кнопка логина")
    public boolean isLoginButtonDisplayed() {
        logger.info("Проверяем видна ли кнопка логина");
        return loginButton.isDisplayed();
    }

    @Step("Проверяем можно ли поставить фокус на кнопку логина")
    public String isLoginButtonFocusable() {
        logger.info("Проверяем можно ли поставить фокус на кнопку логина");
        return loginButton.getDomAttribute("focusable");
    }

    @Step("Проверяем кликабельность кнопки логина")
    public String isLoginButtonClickable() {
        logger.info("Проверяем кликабельность кнопки логина");
        return loginButton.getDomAttribute("clickable");
    }

    @Step("Получаем тест кнопки логина")
    public String getLoginButtonText() {
        logger.info("Получаем тест кнопки логина");
        waitForElementVisible(showPasswordIcon);
        return loginButton.getText();
    }

    @Step("Ожидаем пока не будет виден заголовок страницы логина")
    public void waitPageTitleNotVisible() {
        logger.info("Ожидаем пока не будет виден заголовок страницы логина");
        waitForElementNotVisible(pageTitle);
    }


    @Step("Сохраняем скриншот с полем ввода пароля")
    public LoginPage savePasswordFieldScreenshot(String path, String fileName) {
        logger.info("Сохраняем скриншот с полем ввода пароля");
        saveScreenshot(getPasswordFieldScreenshot(), path, fileName);
        return this;
    }

    @Step("Получаем скриншот с полем ввода пароля")
    public BufferedImage getPasswordFieldScreenshot() {
        logger.info("Получаем скриншот с полем ввода пароля");
        return getScreenshot(passwordField);
    }

    @Step("Получаем заголовок станицы")
    public String getPageTitle() {
        logger.info("Получаем заголовок станицы");
        waitForElementVisible(pageTitle);
        return pageTitle.getText();
    }

    @Step("Кликаем на иконку показать/скрыть пароль'")
    public LoginPage clickShowPasswordIcon(int count) {
        logger.info("Кликаем на иконку показать/скрыть пароль'");
        waitForElementVisible(showPasswordIcon);
        click(showPasswordIcon, count);
        return this;
    }

    @Step("Получаем состояние иконки 'показать/скрыть пароль'")
    public String getShowPasswordIconCheckedState() {
        logger.info("Получаем состояние иконки 'показать/скрыть пароль'");
        waitForElementVisible(showPasswordIcon);
        return showPasswordIcon.getDomAttribute("checked");
    }
}