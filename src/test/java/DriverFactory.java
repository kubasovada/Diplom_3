import config.EnvConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {
    WebDriver driver;

    public DriverFactory() {
         if ("yandex".equals(System.getProperty("browser")))
            setUpYandex();
        else
            driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void setUpYandex() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\chromedriver116.exe");
        options.addArguments("--remote-allow-origins=*");
        options.setBinary(EnvConfig.PATH_BROWSER_YANDEX_EXE);
        driver = new ChromeDriver(options);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void killDriver() {
        driver.quit();
    }

//    public static final String PATH_DRIVER_YANDEX_EXE ="C:\\tools\\yaDriver\\116\\chromedriver116.exe";
//    public void setUpYandex() {
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        ChromeDriverService service = new ChromeDriverService.Builder()
//                .usingDriverExecutable(new File(PATH_DRIVER_YANDEX_EXE))
//                .build();
//        ChromeOptions options = new ChromeOptions()
//                .setBinary(PATH_BROWSER_YANDEX_EXE);
//        driver = new ChromeDriver(service, options);
//    }

}
