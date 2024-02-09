package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Base {
    public WebDriver driver;
    public Actions action;


    public Base(WebDriver driver){

        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.action = new Actions(driver);
        driver.manage().window().maximize();
    }


    @Step("Waiting for page loading...")
    public void waitForPageLoad(int seconds){
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            //Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    @Step("Go to page: {url}")
    public void getUrl(String url) {
        this.driver.get(url);
    }


    @Step("Delete all cookies")
    public void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }
}
