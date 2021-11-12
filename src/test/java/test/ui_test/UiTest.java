package test.ui_test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import runner.AbstractTest;
import utilities.driver.WebDriverManager;

public class UiTest extends AbstractTest {
    private WebDriverManager driverManager = new WebDriverManager();
    @Test(groups = "all")
    public void uiTest(){
        System.out.println("start ui test 1");
        WebDriver driver = driverManager.getDriver();
        System.out.println("test 1, threadId - " + Thread.currentThread().getId() + " webDriver - " + driver.toString());
        driver.get("https://www.google.com/");
        System.out.println(driver.getTitle());
        System.out.println("end ui test 1");
    }
    @Test(groups = "all")
    public void uiTest2(){
        System.out.println("start ui test 2");
        WebDriver driver = driverManager.getDriver();
        System.out.println("test 2, threadId - " + Thread.currentThread().getId() + " webDriver - " + driver.toString());
        driver.get("https://twitter.com/?lang=en");
        System.out.println(driver.getTitle());
        System.out.println("end ui test 2");
    }
    @Test(groups = "all")
    public void uiTest3(){
        System.out.println("start ui test 3");
        WebDriver driver = driverManager.getDriver();
        System.out.println("test 3, threadId - " + Thread.currentThread().getId() + " webDriver - " + driver.toString());
        driver.get("https://www.facebook.com/");
        System.out.println(driver.getTitle());
        System.out.println("end ui test 3");
    }
}
