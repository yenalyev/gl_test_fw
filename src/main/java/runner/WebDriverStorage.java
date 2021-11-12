package runner;

import org.openqa.selenium.WebDriver;
import utilities.log.Log;

import java.util.concurrent.ConcurrentHashMap;

public class WebDriverStorage {
    public ConcurrentHashMap<Long, WebDriver> webDriverMap = new ConcurrentHashMap<>();
    private static WebDriverStorage INSTANCE;

    //thread safe singleton
    public static synchronized WebDriverStorage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WebDriverStorage();
        }
        return INSTANCE;
    }

    public static WebDriver getDriverFromStorage(long threadId){
        WebDriverStorage storage = getInstance();
        return storage.webDriverMap.getOrDefault(threadId, null);
    }
    public static void removeFromStorage(long threadId){
        WebDriverStorage storage = getInstance();
        storage.webDriverMap.remove(threadId);
    }

    public static void setWebDriverToStorage(long threadId, WebDriver driver){
        WebDriverStorage storage = getInstance();
        storage.webDriverMap.put(threadId, driver);
        Log.Debug("Put into storage webdriver " + driver.toString() + " for threadId - " + threadId);
    }
}
