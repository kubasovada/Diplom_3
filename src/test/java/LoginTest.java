import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    DriverFactory factory = new DriverFactory();
    RegistrationPage registrationPage;
    LoginPage loginPage;
    MainPage mainPage;

    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10)+"@ya.ru";
    private String password = RandomStringUtils.randomAlphanumeric(10);


    @Before
    public void setUp() {
        registrationPage = new RegistrationPage(factory.getDriver());
        registrationPage.open();
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickOnRegistrationButton();
    }

    @After
    public void killDriver() {
        factory.killDriver();
    }

    @Test
    @DisplayName("login from main page")
    public void loginFromMainPageTest() {
        mainPage = new MainPage(factory.getDriver());
        mainPage.open();
        mainPage.clickOnLoginButton();
        loginPage = new LoginPage(factory.getDriver());
        loginPage.login(email, password);
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("login from personal account page")
    public void loginFromPersonalAccountTest() {
        mainPage = new MainPage(factory.getDriver());
        mainPage.open();
        mainPage.clickOnPersonalAccount();
        loginPage = new LoginPage(factory.getDriver());
        loginPage.login(email, password);
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("login from register page")
    public void loginFromRegisterPageTest() {
        registrationPage.open();
        registrationPage.clickOnLoginButton();
        loginPage = new LoginPage(factory.getDriver());
        loginPage.login(email,password);
        mainPage = new MainPage(factory.getDriver());
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }


    @Test
    @DisplayName("login from reset password page")
    public void loginFromResetPasswordPageTest() {
        loginPage = new LoginPage(factory.getDriver());
        loginPage.clickOnForgotPasswordButton();
        loginPage.login(email,password);
        mainPage = new MainPage(factory.getDriver());
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }
}
