import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest {
    DriverFactory factory = new DriverFactory();
    MainPage mainPage;

    @Before
    public void setUp() {
        mainPage = new MainPage(factory.getDriver());
    }

    @After
    public void killDriver() {
        factory.killDriver();
    }
    @Test
    @DisplayName("check Constructor Sauce at MainPage")
    public void checkSauceLink() {
        mainPage.open();
        assertTrue(mainPage.checkSauceIsDisplayed());
    }

    @Test
    @DisplayName("check Constructor Filing at MainPage")
    public void checkFillingLink() {
        mainPage.open();
        assertTrue(mainPage.checkFillingIsDisplayed());
    }

    @Test
    @DisplayName("check Constructor Buns at MainPage")
    public void checkBunsLink() {
        mainPage.open();
        assertTrue(mainPage.checkBunsIsDisplayed());
    }

}
