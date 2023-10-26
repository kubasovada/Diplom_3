import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;
import java.time.Duration;

public class DriverFactory {
    WebDriver driver;


    public DriverFactory() {
        if ("firefox".equals(System.getProperty("browser")))
            setUpFirefox();
        if ("yandex".equals(System.getProperty("browser")))
            setUpYandex();
        else
            driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void setUpFirefox() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        var service = new GeckoDriverService.Builder()
                .usingDriverExecutable(new File("C:\\tools\\geckodriver.exe"))
                .build();
        var options = new FirefoxOptions()
                .setBinary("C:\\Program Files\\Firefox 115.3\\firefox.exe");
        driver = new FirefoxDriver(service, options);
    }

    public void setUpYandex() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("C:\\tools\\yaDriver\\116\\chromedriver.exe"))
                .build();
        ChromeOptions options = new ChromeOptions()
                .setBinary("C:\\Users\\Dkubasova\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(service, options);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void killDriver() {
        driver.quit();
    }
}
