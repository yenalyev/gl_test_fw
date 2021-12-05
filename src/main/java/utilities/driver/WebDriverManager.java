package utilities.driver;

import org.openqa.selenium.WebDriver;
import runner.WebDriverStorage;
import utilities.log.Log;

import java.util.List;

import static utilities.properties.CommonProperties.WEBDRIVER_REUSABLE_POLICY;

public class WebDriverManager {
    public WebDriver getDriver(){
        long threadId = Thread.currentThread().getId();
        WebDriver driver = WebDriverStorage.getDriverFromStorage(threadId);
        if (null==driver){
            Log.Info("WebDriver for thread id - " + threadId + " is missing in storage");
            driver = WebDriverFactory.createDriver();
            Log.Info("WebDriver for thread id - " + threadId + " was created");
            WebDriverStorage.setWebDriverToStorage(threadId, driver);
        }
        Log.Info("Return webDriver "+ driver.toString() +" for threadId - " + threadId + " from webDriverManager");
        return driver;
    }

    public void quit(){
        long threadId = Thread.currentThread().getId();
        WebDriver driver;
        switch (WEBDRIVER_REUSABLE_POLICY.toLowerCase()){
            case "reuse":
                driver = getDriver();
                driver.manage().deleteAllCookies();
                Log.Info("delete all cookies for webdriver " + driver);
                WebDriverStorage.setWebDriverToStorage(threadId, driver);
                break;
            default:
                driver = getDriver();
                if (driver!=null){
                    driver.quit();
                }
                WebDriverStorage.removeFromStorage(threadId);
        }
    }


    public void quitAll(){
        List<WebDriver> drivers = WebDriverStorage.getDAllDriversFromStorage();
        if (null!=drivers && !drivers.isEmpty()){
            for (WebDriver driver: drivers){
                if (driver!=null){
                    driver.quit();
                }
            }
        }
    }

}
