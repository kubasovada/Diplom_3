package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    WebDriver driver;
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site";
    private final By makeOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By loginButtonOnMainPage = By.xpath(".//button[text()='Войти в аккаунт']");

    private final By personalAccountButtonOnMainPage = By.xpath(".//a[@href='/account']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step ("Open main page")
    public void open() {
        driver.get(MAIN_PAGE_URL);
    }

    @Step("Get text from order button on MainPage")
    public String textOrderButton () {
        WebElement textButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(makeOrderButton));
        return textButton.getText();
    }
    @Step("Click on logIn button on Main Page")
    public void clickOnLoginButton() {
        driver.findElement(loginButtonOnMainPage).click();
    }
    @Step("Click on personal account button on Main Page")
    public void clickOnPersonalAccountButton() {
        driver.findElement(personalAccountButtonOnMainPage).click();
    }


}
