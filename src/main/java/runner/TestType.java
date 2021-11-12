package runner;

public enum TestType {
    API_TEST("test.api_test"),
    UI_TEST("test.ui_test"),
    ALL_TESTS("test.*");

    public final String testPackage;
    TestType(String testPackage){
        this.testPackage = testPackage;
    }
}
