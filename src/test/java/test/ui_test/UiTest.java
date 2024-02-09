package test.ui_test;

import io.qameta.allure.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.AbstractTest;
import ui.pages.Base;
import utilities.driver.WebDriverManager;
import utilities.log.Log;

import java.io.ByteArrayInputStream;

import static utilities.properties.CommonProperties.SELENIUM_DRIVER_WAIT_TIMEOUT;


@Epic("Smoke tests")
public class UiTest extends AbstractTest {
    private WebDriverManager driverManager = new WebDriverManager();

    @Test(groups = {"all"})
    @Description("Check page title")
    @TmsLink("TMS-456")
    public void uiTest(){
        Base base = new Base(driver);
        base.getUrl("https://www.google.com/");
        base.waitForPageLoad(SELENIUM_DRIVER_WAIT_TIMEOUT);
        String title = driver.getTitle();
        Allure.addAttachment("Screen page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assert.assertEquals(title, "Google");
    }
    @Test(groups = "all")
    public void uiTest2(){
        Log.Info("Start uiTest2 with thread id - " + Thread.currentThread().getId());

        Base base = new Base(driver);
        base.getUrl("https://twitter.com/?lang=en");
        base.waitForPageLoad(SELENIUM_DRIVER_WAIT_TIMEOUT);
        String title = driver.getTitle();
        Allure.addAttachment("Screen page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assert.assertEquals(title, "Twitter. It’s what’s happening / Twitter");

    }
    @Test(groups = "all")
    public void uiTest3(){
        Log.Info("Start uiTest3 with thread id - " + Thread.currentThread().getId());
        Base base = new Base(driver);
        base.getUrl("https://www.facebook.com/");
        base.waitForPageLoad(SELENIUM_DRIVER_WAIT_TIMEOUT);
        String title = driver.getTitle();
        Allure.addAttachment("Screen page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assert.assertEquals(title, "Facebook - Log In or Sign Up");
    }

    @Test(groups = "all")
    public void uiTest4(){
        Log.Info("Start uiTest4 with thread id - " + Thread.currentThread().getId());
        Base base = new Base(driver);
        base.getUrl("https://www.geeksforgeeks.org");
        base.waitForPageLoad(SELENIUM_DRIVER_WAIT_TIMEOUT);
        String title = driver.getTitle();
        Allure.addAttachment("Screen page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assert.assertFalse(title.isEmpty());
    }

    @Test(groups = "all")
    public void uiTest5(){
        Log.Info("Start uiTest5 with thread id - " + Thread.currentThread().getId());
        driver.get("https://stackoverflow.com/");
        Base base = new Base(driver);
        base.waitForPageLoad(SELENIUM_DRIVER_WAIT_TIMEOUT);
        String title = driver.getTitle();
        Allure.addAttachment("Screen page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assert.assertFalse(title.isEmpty());
    }

    @Test(groups = "all")
    public void uiTest6(){
        Log.Info("Start uiTest6 with thread id - " + Thread.currentThread().getId());
        driver.get("https://www.foxtrot.com.ua/");
        Base base = new Base(driver);
        base.waitForPageLoad(SELENIUM_DRIVER_WAIT_TIMEOUT);
        String title = driver.getTitle();
        Assert.assertFalse(title.isEmpty());
    }

    @Test(groups = "all")
    public void uiTest7(){
        Log.Info("Start uiTest7 with thread id - " + Thread.currentThread().getId());
        Base base = new Base(driver);
        base.getUrl("https://www.swtestacademy.com/");
        base.waitForPageLoad(SELENIUM_DRIVER_WAIT_TIMEOUT);
        String title = driver.getTitle();
        Allure.addAttachment("Screen page", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Assert.assertFalse(title.isEmpty());
    }

}
