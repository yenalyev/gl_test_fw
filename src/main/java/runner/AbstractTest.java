package runner;

import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.driver.WebDriverManager;
import utilities.log.Log;

import static runner.TestType.API_TEST;
import static runner.TestType.UI_TEST;

public class AbstractTest {
    public WebDriver driver;

    @BeforeClass (alwaysRun = true)
    public void initClass(ITestContext testContext){
        String packageName = this.getClass().getPackage().getName();
        if (packageName.contains(UI_TEST.testPackage)){
            System.out.println("UI Tests");
            System.out.println(packageName);

//            if (testContext.getSuite().getParallel().equals("classes")){
//                WebDriverManager webDriverPool = new WebDriverManager();
//                driver = webDriverPool.getDriver();
//            }

        } else if (packageName.contains(API_TEST.testPackage)){
            System.out.println("API Tests");
            System.out.println(packageName);
        } else {
            System.out.println("ALL Tests");
            System.out.println(packageName);
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void initMethod(ITestContext testContext){
        String packageName = this.getClass().getPackage().getName();
        if (packageName.contains(UI_TEST.testPackage)){
            System.out.println("UI Tests before method");
//            if (testContext.getSuite().getParallel().equals("methods")){
//                WebDriverManager webDriverPool = new WebDriverManager();
//                driver = webDriverPool.getDriver();
//            }
        } else if (packageName.contains(API_TEST.testPackage)){
            System.out.println("API Tests before method");
            System.out.println(packageName);
        }
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestContext testContext, ITestResult testResult){
        String packageName = this.getClass().getPackage().getName();
        if (packageName.contains(UI_TEST.testPackage)){
            Log.Info("UI Tests after method");
            if (testContext.getSuite().getParallel().equals("methods")){
                Log.Info("Check quite driver for method - " + testResult.getMethod().getMethodName());
                WebDriverManager webDriverPool = new WebDriverManager();
                webDriverPool.quit();
            }
        } else if (packageName.contains(API_TEST.testPackage)){
            System.out.println("API Tests before method");
            System.out.println(packageName);
        }
    }


    @AfterClass(alwaysRun = true)
    public void afterClass(ITestContext testContext){
        String packageName = this.getClass().getPackage().getName();
        if (packageName.contains(UI_TEST.testPackage)){
            System.out.println("UI Tests after method");
            if (testContext.getSuite().getParallel().equals("classes")){
                WebDriverManager webDriverPool = new WebDriverManager();
                webDriverPool.quit();
            }
        } else if (packageName.contains(API_TEST.testPackage)){
            System.out.println("API Tests before method");
            System.out.println(packageName);
        }
    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite(ITestContext testContext){
        String packageName = this.getClass().getPackage().getName();
        if (packageName.contains(UI_TEST.testPackage)){
            System.out.println("UI Tests after suite");
            WebDriverManager webDriverPool = new WebDriverManager();
            webDriverPool.quitAll();
            WebDriverStorage.deleteAll();
        } else if (packageName.contains(API_TEST.testPackage)){
            System.out.println("API Tests before method");
            System.out.println(packageName);
        }
    }

}
