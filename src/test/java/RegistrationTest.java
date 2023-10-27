import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RegistrationTest {
    DriverFactory factory = new DriverFactory();

    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10)+"@ya.ru";
    RegistrationPage registrationPage;

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
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("check error message when password less 6 numbers")
    public void registrationWithErrorTest() {
        String password = "123";
        registrationPage.open();
        registrationPage.fillRegistrationForm(name, email,password);
        registrationPage.clickOnRegistrationButton();
        assertTrue(registrationPage.getErrorMessage().isDisplayed());
    }

}
