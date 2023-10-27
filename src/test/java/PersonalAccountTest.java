import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccountPage;
import pages.RegistrationPage;

import static org.junit.Assert.*;

public class PersonalAccountTest {

    DriverFactory factory = new DriverFactory();
    RegistrationPage registrationPage;
    LoginPage loginPage;
    MainPage mainPage;
    PersonalAccountPage personalAccountPage;

    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10)+"@ya.ru";
    private String password = RandomStringUtils.randomAlphanumeric(10);


    @Before
    public void setUp() {
        registrationPage = new RegistrationPage(factory.getDriver());
        registrationPage.open();
        registrationPage.fillRegistrationForm(name, email, password);
        registrationPage.clickOnRegistrationButton();
        loginPage = new LoginPage(factory.getDriver());
        mainPage = new MainPage(factory.getDriver());
    }

    @After
    public void killDriver() {
        factory.killDriver();
    }

    @Test
    @DisplayName("check link to Personal Account")
    public void checkLinkToPersonalAccount() {
        loginPage.login(email, password);
        mainPage.clickOnPersonalAccountButton();
        personalAccountPage = new PersonalAccountPage(factory.getDriver());
        assertEquals("Выход", personalAccountPage.checkLogOutButtonInPersonalAccount());
    }

    @Test
    @DisplayName("check exit from Personal Account")
    public void checkExitFromPersonalAccount() {
        loginPage.login(email, password);
        mainPage.clickOnPersonalAccountButton();
        personalAccountPage = new PersonalAccountPage(factory.getDriver());
        personalAccountPage.clickExitButtonInPersonalAccount();
        assertEquals("Войти", loginPage.checkLogInButton());
    }

    @Test
    @DisplayName("check click on logo from Personal Account and go to main page")
    public void checkClickOnLogoFromPersonalAccount() {
        loginPage.login(email, password);
        mainPage.clickOnPersonalAccountButton();
        personalAccountPage = new PersonalAccountPage(factory.getDriver());
        personalAccountPage.clickOnLogoFromPersonalAccount();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("check click on constructor from Personal Account and go to main page")
    public void checkClickOnConstructorFromPersonalAccount() {
        loginPage.login(email, password);
        mainPage.clickOnPersonalAccountButton();
        personalAccountPage = new PersonalAccountPage(factory.getDriver());
        personalAccountPage.clickOnConstructorFromPersonalAccount();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

}
