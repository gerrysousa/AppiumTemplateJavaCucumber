package base;

import static helpers.DriverFactory.getDriver;

import helpers.DriverFactory;
import helpers.ExtentReportsUtils;
import helpers.GlobalParameters;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import java.io.IOException;
//import java.lang.reflect.Method;
import java.lang.reflect.Method;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.platform.engine.TestExecutionResult;

@TestInstance(Lifecycle.PER_CLASS)
public class BaseTests {
    protected AppiumDriver<MobileElement> driver = getDriver();

    @BeforeAll
    public static void beforeSuite() {
        new GlobalParameters();
      //  new DriverFactory();
        ExtentReportsUtils.createReport();
    }

    @BeforeEach
    public void beforeMethod(TestInfo method) {
        ExtentReportsUtils.addTest(method.getDisplayName(), method.getTestClass().get().getName());
    }
    /*
    @BeforeEach
    public void beforeMethod(Method method) {
        ExtentReportsUtils.addTest(method.getName(), method.getDeclaringClass().getSimpleName());
    }

    @AfterEach
    public void afterMethod(TestExecutionResult result) throws IOException {
        ExtentReportsUtils.addTestResult(result);
        ExtentReportsUtils.generateReport();
        DriverFactory.killDriver();
    }
    * */


    @AfterEach
    public void afterMethod(TestExecutionResult result) throws IOException {
        ExtentReportsUtils.addTestResult(result);
        ExtentReportsUtils.generateReport();
        DriverFactory.killDriver();
    }

    @AfterAll
    public static void afterSuite() {
        ExtentReportsUtils.generateReport();
        DriverFactory.killDriver();
    }

    /**
     * Sets thread name which includes thread id
     */
    private void setCurrentThreadName() {
        Thread thread = Thread.currentThread();
        String threadName = thread.getName();
        String threadId = String.valueOf(thread.getId());
        if (!threadName.contains(threadId)) {
            thread.setName(threadName + " " + threadId);
        }
    }
}