package utilities.properties;

import utilities.helper.ListHelper;

import java.util.List;

public class CommonProperties {

    //-------------------------------Selenium-------------------------------------------
    public final static String BROWSER_TYPE = PropertyLoader.getProperty("browser.type", "chromer");
    public final static String BROWSER_VERSION = "browser.version";
    public final static String PROJECT_NAME = "project.name";
    public static final String SCREENSHOT_OFF = "screenshot.off";
    public final static String SELENOID_URL =  PropertyLoader.getProperty("selenoid.url", "localhost");
    public final static Integer SELENIUM_DRIVER_WAIT_TIMEOUT =
            Integer.parseInt(PropertyLoader.getProperty("selenium.driver.wait.timeout", 10));
    public final static String SELENOID_ENABLE_VNC = "selenium.driver.wait.timeout";
    public static final String WEBDRIVER_REUSABLE_POLICY =
            PropertyLoader.getProperty("webdriver.reusable.policy", "reuse");

    //-------------------------TestNG-------------------------------------
    public static final String TESTNG_PARALLELIZATION_MODE = PropertyLoader.getProperty("testng.parallelization.mode");
    public static final String TESTNG_THREADS_QTY = PropertyLoader.getProperty("testng.parallelization.thread.qty");
    public static final String TESTNG_TEST_TYPE = PropertyLoader.getProperty("testng.test.type");
    public static final String TESTNG_SUITE_NAME = PropertyLoader.getProperty("testng.suite.name");
    public static final String TESTNG_TEST_GROUPS_CONFIG = PropertyLoader.getProperty("testng.test.group");
    public static final List<String> TESTNG_TEST_GROUPS =
            ListHelper.stringIntoListBySeparator(TESTNG_TEST_GROUPS_CONFIG,",");

}
