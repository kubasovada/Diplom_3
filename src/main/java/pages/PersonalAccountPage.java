package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    WebDriver driver;
    private final By logOutButton = By.xpath(".//button[text()='Выход']");
    private final By logo = By.className("AppHeader_header__logo__2D0X2");
    private final By constructorInHeader = By.xpath(".//p[text()='Конструктор']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Find And get text from exit Button on personal account")
    public String checkLogOutButtonInPersonalAccount() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
        return driver.findElement(logOutButton).getText();
    }

    @Step("Click exit button in personal account")
    public void clickExitButtonInPersonalAccount() {
        driver.findElement(logOutButton).click();
    }

    @Step("Click on logo from personal account")
    public void clickOnLogoFromPersonalAccount() {
        driver.findElement(logo).click();
    }

    @Step("Click on constructor from personal account")
    public void clickOnConstructorFromPersonalAccount() {
        driver.findElement(constructorInHeader).click();
    }
}
