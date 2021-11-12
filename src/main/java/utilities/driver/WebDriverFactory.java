package utilities.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.log.Log;
import utilities.properties.CommonProperties;
import utilities.properties.PropertyLoader;

import java.net.MalformedURLException;
import java.net.URI;

import static utilities.properties.CommonProperties.BROWSER_TYPE;

public class WebDriverFactory {
    public static WebDriver createDriver(){
        WebDriver instance = null;

        switch (BROWSER_TYPE.toLowerCase()) {
            case "chrome":
                instance = initWebDriver("chrome");
                break;
            case "chromer":
                instance = initRemoteDriver("chrome");
                break;
            case "firefox":
                instance = initWebDriver("firefox");
                break;
            case "firefoxr":
                instance = initRemoteDriver("firefox");
                break;
            default:
                instance = initWebDriver("CHROME");;
        }
        return instance;
    }

    private static WebDriver initWebDriver(String driverName) {
        switch (driverName) {

            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                WebDriver firefoxDriver = new FirefoxDriver();
                firefoxDriver.manage().window().maximize();
                return firefoxDriver;

            default:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setHeadless(true);
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
                WebDriver chromeDriver = new ChromeDriver(options);
                chromeDriver.manage().window().maximize();
                return chromeDriver;
        }
    }

    public static RemoteWebDriver initRemoteDriver(String browser){
        Log.Info("Start remoteVebDriver for - " + browser);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(browser);
        //capabilities.setVersion(PropertyLoader.getProperty(CommonProperties.BROWSER_VERSION));
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        capabilities.setJavascriptEnabled(true);
        capabilities.setCapability("name", PropertyLoader.getProperty(CommonProperties.PROJECT_NAME));

        RemoteWebDriver driver = null;
        try {
            driver = new RemoteWebDriver(
                    URI.create(PropertyLoader.getProperty(CommonProperties.SELENOID_URL)).toURL(),
                    capabilities
            );
            driver.manage().window().setSize(new Dimension(1920, 1080));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;

    }

}
