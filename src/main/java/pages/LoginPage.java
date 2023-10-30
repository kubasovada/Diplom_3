package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    private final By emailField = By.xpath(".//input[@name='name']");
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By forgotPasswordButton = By.xpath(".//a[@href='/forgot-password']");
    private final By logButtonFromForgotPassword = By.xpath(".//a[@href='/login']");
    private final By authTitleEnter = By.xpath(".//main//h2");
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
            }

    @Step ("Open login page")
    public void open() {
        driver.get(LOGIN_URL);
    }
    @Step("Fill email, password for login and click button")
    public void login(String email, String password) {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.textToBe(authTitleEnter, "Вход"));
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    @Step ("Wait and click on forgot password button")
    public void clickOnForgotPasswordButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(forgotPasswordButton));
        driver.findElement(forgotPasswordButton).click();
        driver.findElement(logButtonFromForgotPassword).click();
    }

    @Step ("Check login button on login page")
    public String checkLogInButton() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(loginButton));
        return driver.findElement(loginButton).getText();

    }
}
