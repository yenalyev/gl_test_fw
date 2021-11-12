package runner;

import org.testng.TestNG;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utilities.log.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static utilities.properties.ApplicationConfig.*;
import static utilities.properties.CommonProperties.*;
import static runner.TestType.API_TEST;
import static runner.TestType.UI_TEST;

public class Runner {

    public static void run(){
        List<XmlSuite> list = new ArrayList<>();
        list.add(testNgXmlSuiteCreate());
        TestNG tng = new TestNG();
        tng.setXmlSuites(list);
        tng.run();
    }
    /**
     * Update testNG xml file for suite from properties
     * @return
     */
    public static XmlSuite testNgXmlSuiteAlter(XmlSuite suite) {
        //set suite name
        setSuiteName(suite);
        //set parallel mode
        setSuiteParallelizationType(suite);
        //set thread qty
        setSuiteThreadCount(suite);
        //set package
        XmlTest test = new XmlTest(suite);
        test.setName("test");
        setTestPackages(suite);

        HashMap<String, String> suiteParams = new HashMap<>();
        suite.setParameters(suiteParams);
        //included groups
        setIncludedGroups(suite);
        //excluded groups
        //TODO create excluded group functionality
        Log.Info("create TestNG configuration xml file ");
        Log.Info("******************************************************");
        Log.Info(suite.toXml());
        Log.Info("******************************************************");
        return suite;
    }

    /**
     * Create testNG xml file for suite from properties
     * @return
     */
    public static XmlSuite testNgXmlSuiteCreate() {
        XmlSuite suite = new XmlSuite();
        //set suite name
        setSuiteName(suite);
        //set parallel mode
        setSuiteParallelizationType(suite);
        //set thread qty
        setSuiteThreadCount(suite);
        //set package
        XmlTest test = new XmlTest(suite);
        test.setName("test");
        setTestPackages(suite);

        HashMap<String, String> suiteParams = new HashMap<>();
        suite.setParameters(suiteParams);
        //included groups
        setIncludedGroups(suite);
        //excluded groups
        //TODO create excluded group functionality
        Log.Info("create TestNG configuration xml file ");
        Log.Info("******************************************************");
        Log.Info(suite.toXml());
        Log.Info("******************************************************");
        return suite;
    }

    //included groups
    private static void setIncludedGroups(XmlSuite suite){
        if (TESTNG_TEST_GROUPS.isEmpty()){
            suite.addIncludedGroup(DEFAULT_TEST_GROUP);
            Log.Warn("Missing suiteName parameter");
        } else {
            for (String s: TESTNG_TEST_GROUPS){
                suite.addIncludedGroup(s);
            }
        }
    }

    //parallel in suite
    private static void setSuiteParallelizationType(XmlSuite suite) {
        if (TESTNG_PARALLELIZATION_MODE == null) {
            suite.setParallel(XmlSuite.ParallelMode.NONE);
            return;
        }
        String parallelMode = TESTNG_PARALLELIZATION_MODE.toLowerCase().trim();
        switch (parallelMode) {
            case "classes":
                suite.setParallel(XmlSuite.ParallelMode.CLASSES);
                break;
            case "methods":
                suite.setParallel(XmlSuite.ParallelMode.METHODS);
                break;
            case "tests":
                suite.setParallel(XmlSuite.ParallelMode.TESTS);
                break;
            case "instances":
                suite.setParallel(XmlSuite.ParallelMode.INSTANCES);
                break;
            default:
                suite.setParallel(XmlSuite.ParallelMode.NONE);
        }
    }

    // thread count in suite
    private static void setSuiteThreadCount(XmlSuite suite){
        if (TESTNG_THREADS_QTY == null) {
            suite.setThreadCount(MIN_TESTNG_THREADS);
            return;
        }
        try {
            int threadQty = Integer.parseInt(TESTNG_THREADS_QTY);
            if (threadQty<MIN_TESTNG_THREADS){
                threadQty = MIN_TESTNG_THREADS;
            } else if (threadQty > MAX_TESTNG_THREADS){
                threadQty = MAX_TESTNG_THREADS;
            }
            suite.setThreadCount(threadQty);
        } catch (NumberFormatException e){
            suite.setThreadCount(MIN_TESTNG_THREADS);
            Log.Error("Error while set testNG suite thread qty");
            Log.Error(e.getMessage());
            Log.Info("Set "+MIN_TESTNG_THREADS+" thread for testNg suite - " + suite.getName());
        }
    }

    //set test package
    private static void setTestPackages(XmlSuite suite){
        List<XmlPackage> packages = new ArrayList<>();
        if (TESTNG_TEST_TYPE==null){
            packages.add(new XmlPackage(TestType.ALL_TESTS.testPackage));
            suite.getTests().get(0).setPackages(packages);
            return;
        }
        switch (TESTNG_TEST_TYPE){
            case "api":
                packages.add(new XmlPackage(API_TEST.testPackage));
                suite.getTests().get(0).setPackages(packages);
                return;
            case "ui":
                packages.add(new XmlPackage(UI_TEST.testPackage));
                suite.getTests().get(0).setPackages(packages);
                return;
            default:
                packages.add(new XmlPackage(TestType.ALL_TESTS.testPackage));
                suite.getTests().get(0).setPackages(packages);
        }
    }

    //set test package
    private static void setSuiteName(XmlSuite suite){
        if (TESTNG_SUITE_NAME==null){
            suite.setName(DEFAULT_SITE_NAME);
        } else {
         suite.setName(TESTNG_SUITE_NAME);
        }
    }

}
