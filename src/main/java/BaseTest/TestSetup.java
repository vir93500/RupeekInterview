package BaseTest;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestSetup {

    public  SoftAssert softAssert;
    @BeforeSuite
    public void beforeSuite(){
    }

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {

    }

    @BeforeMethod(alwaysRun = true)
    public void setUpBeforeMethod(Method method) {
        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {

    }


    @AfterSuite(alwaysRun = true)
    public void afterSuite() throws IOException {

    }
}
