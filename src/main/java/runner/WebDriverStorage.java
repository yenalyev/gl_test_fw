package runner;

import org.openqa.selenium.WebDriver;
import utilities.log.Log;

import java.util.ArrayList;
import java.util.List;
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

    public static List<WebDriver> getDAllDriversFromStorage(){
        WebDriverStorage storage = getInstance();
        List<WebDriver> drivers = new ArrayList<>(storage.webDriverMap.values());
        return drivers;
    }

    public static void removeFromStorage(long threadId){
        WebDriverStorage storage = getInstance();

        WebDriver driver = storage.webDriverMap.getOrDefault(threadId, null);
        String driverInfo = driver.toString();

        storage.webDriverMap.remove(threadId);
        Log.Info("Remove from storage webdriver " + driverInfo + " for threadId - " + threadId);

    }

    public static void setWebDriverToStorage(long threadId, WebDriver driver){
        WebDriverStorage storage = getInstance();
        storage.webDriverMap.put(threadId, driver);
        Log.Info("Put into storage webdriver " + driver.toString() + " for threadId - " + threadId);
    }

    public static void deleteAll(){
        WebDriverStorage storage = getInstance();
        storage.webDriverMap.clear();
        Log.Info("WebDriver storage was cleared");
    }
}
