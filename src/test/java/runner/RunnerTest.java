package runner;

import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

public class RunnerTest {

    @Test
    public void testTestNgXmlSuiteCreate() {
        XmlSuite suite = Runner.testNgXmlSuiteCreate();
        System.out.println(suite.toXml());
    }
}