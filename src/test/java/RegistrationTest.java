import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.RestClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RegistrationTest {
    DriverFactory factory = new DriverFactory();
    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10)+"@ya.ru";
    RegistrationPage registrationPage;
    RestClient restClient = new RestClient();
    String accessToken;


    @Before
    public void setUp() {
        registrationPage = new RegistrationPage(factory.getDriver());
    }

    @After
    public void killDriver() {
        factory.killDriver();
    }
    @Test
    @DisplayName("check success registration")
    public void registrationTest() {
        String password = RandomStringUtils.randomAlphanumeric(10);
        registrationPage.open();
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickOnRegistrationButton();
        LoginPage loginPage = new LoginPage(factory.getDriver());
        loginPage.login(email, password);
        MainPage mainPage = new MainPage(factory.getDriver());
        accessToken = mainPage.getAccessFromLocalStorage();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
        restClient.delete(accessToken);
    }

    @Test
    @DisplayName("check error message when password less 6 numbers")
    public void registrationWithErrorTest() {
        String incorrectPassword = "123";

        try{
            registrationPage.open();
            registrationPage.fillRegistrationForm(name, email,incorrectPassword);
            registrationPage.clickOnRegistrationButton();
            assertTrue(registrationPage.getErrorMessage().isDisplayed());
        }
        catch(Exception exception){
            LoginPage loginPage = new LoginPage(factory.getDriver());
            loginPage.login(email, incorrectPassword);
            MainPage mainPage = new MainPage(factory.getDriver());
            mainPage.checkFillingIsDisplayed();
            accessToken = mainPage.getAccessFromLocalStorage();
            restClient.delete(accessToken);
            assertTrue(registrationPage.getErrorMessage().isDisplayed()); // фейлю тест, но при этом удаляю созданного юзера
        }
    }

}
