import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.RestClient;
import pages.*;

import static org.junit.Assert.*;

public class PersonalAccountTest {

    DriverFactory factory = new DriverFactory();
    LoginPage loginPage;
    MainPage mainPage;
    PersonalAccountPage personalAccountPage;
    RestClient restClient = new RestClient();
    String accessToken;

    private String name = RandomStringUtils.randomAlphanumeric(10);
    private String email = RandomStringUtils.randomAlphanumeric(10)+"@ya.ru";
    private String password = RandomStringUtils.randomAlphanumeric(10);


    @Before
    public void setUp() {
        ValidatableResponse response = restClient.createUser(name, email, password);
        accessToken = restClient.getAccess(response);
        loginPage = new LoginPage(factory.getDriver());
        mainPage = new MainPage(factory.getDriver());
    }

    @After
    public void killDriver() {
        factory.killDriver();
        restClient.delete(accessToken);
    }

    @Test
    @DisplayName("check link to Personal Account")
    public void checkLinkToPersonalAccount() {
        loginPage.open();
        loginPage.login(email, password);
        mainPage.clickOnPersonalAccountButton();
        personalAccountPage = new PersonalAccountPage(factory.getDriver());
        assertEquals("Выход", personalAccountPage.checkLogOutButtonInPersonalAccount());
    }

    @Test
    @DisplayName("check exit from Personal Account")
    public void checkExitFromPersonalAccount() {
        loginPage.open();
        loginPage.login(email, password);
        mainPage.clickOnPersonalAccountButton();
        personalAccountPage = new PersonalAccountPage(factory.getDriver());
        personalAccountPage.clickExitButtonInPersonalAccount();
        assertEquals("Войти", loginPage.checkLogInButton());
    }

    @Test
    @DisplayName("check click on logo from Personal Account and go to main page")
    public void checkClickOnLogoFromPersonalAccount() {
        loginPage.open();
        loginPage.login(email, password);
        mainPage.clickOnPersonalAccountButton();
        personalAccountPage = new PersonalAccountPage(factory.getDriver());
        personalAccountPage.clickOnLogoFromPersonalAccount();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

    @Test
    @DisplayName("check click on constructor from Personal Account and go to main page")
    public void checkClickOnConstructorFromPersonalAccount() {
        loginPage.open();
        loginPage.login(email, password);
        mainPage.clickOnPersonalAccountButton();
        personalAccountPage = new PersonalAccountPage(factory.getDriver());
        personalAccountPage.clickOnConstructorFromPersonalAccount();
        assertEquals("Оформить заказ", mainPage.textOrderButton());
    }

}
