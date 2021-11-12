package runner;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class TestNgAlterSuiteListener implements IAlterSuiteListener {
    @Override
    public void alter(List<XmlSuite> suites) {
        XmlSuite suite = suites.get(0);
        Runner.testNgXmlSuiteAlter(suite);
    }
}
