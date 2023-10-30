import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.RestClient;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    DriverFactory factory = new DriverFactory();
    RegistrationPage registrationPage;
    LoginPage loginPage;
    MainPage mainPage;
    RestClient restClient = new RestClient();
    String accessToken;

    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10)+"@ya.ru";
    private String password = RandomStringUtils.randomAlphanumeric(10);


    @Before
    public void setUp() {
        ValidatableResponse response = restClient.createUser(name, email, password);
        accessToken = restClient.getAccess(response);
    }

    @After
    public void killDriver() {

        factory.killDriver();
        restClient.delete(accessToken);
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
        mainPage.clickOnPersonalAccountButton();
        loginPage = new LoginPage(factory.getDriver());
        loginPage.login(email, password);
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("login from register page")
    public void loginFromRegisterPageTest() {
        registrationPage = new RegistrationPage(factory.getDriver());
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
        loginPage.open();
        loginPage.clickOnForgotPasswordButton();
        loginPage.login(email,password);
        mainPage = new MainPage(factory.getDriver());
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }
}
