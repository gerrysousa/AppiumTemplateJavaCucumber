package helpers;

import static helpers.DriverFactory.getDriver;
import static helpers.GlobalParameters.params;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.junit.platform.engine.TestExecutionResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class ExtentReportsUtils
{
  public static ExtentReports EXTENT_REPORT = null;
  public static ExtentTest TEST;
  public static ExtentSparkReporter HTML_REPORTER = null;
  static String reportName = params.getREPORT_NAME() + "_" + GeneralUtils.getNowDate("yyyy-MM-dd_HH-mm-ss");
  static String reportsPath = params.getPATH_PROJECT()+params.getREPORT_PATH();
  static String fileName = reportName+".html";
  static String fullReportFilePath = reportsPath + "/"+ fileName;

  public static void createReport(){
    if ("true".equals(params.getREPORT_BY_EXECUTION())) {
      fullReportFilePath = reportsPath + "/" + reportName + "/" + fileName;
      reportsPath = reportsPath + "/" + reportName + "/";
    }
    if (EXTENT_REPORT == null){
      HTML_REPORTER = new ExtentSparkReporter(fullReportFilePath);
      HTML_REPORTER.config().setTheme(Theme.DARK);
      HTML_REPORTER.config().setReportName(params.getREPORT_NAME());
      EXTENT_REPORT = new ExtentReports();
      EXTENT_REPORT.attachReporter(HTML_REPORTER);
    }
  }

  public static void addTest(String testName, String testCategory){
    TEST = EXTENT_REPORT.createTest(testName).assignCategory(testCategory.replace("Tests",""));
  }

  public static void addTestInfo(int methodLevel, String text) {
    if ("true".equals(params.getGET_SCREENSHOT_FOR_EACH_STEP()))
    {
      try {
        String temp= takeScreenshot(getDriver());
        TEST.log(Status.PASS, GeneralUtils.getMethodNameByLevel(methodLevel) + " || " + text, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
      }
      catch (Exception e)
      {
        TEST.log(Status.PASS, GeneralUtils.getMethodNameByLevel(methodLevel) + " || Alert: Problem to take a screenshot!!!!  " + text);
      }
    }
    else
    {
      TEST.log(Status.PASS, GeneralUtils.getMethodNameByLevel(methodLevel) + " || " + text);
    }
  }

  public static void addTestInfoSimple(String text) {
    if ("true".equals(params.getGET_SCREENSHOT_FOR_EACH_STEP()))
    {
      try {
        String temp= takeScreenshot(getDriver());
        TEST.log(Status.PASS,  text, MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
      }
      catch (Exception e)
      {
        TEST.log(Status.PASS, " || Alert: Problem to take a screenshot!!!!  " + text);
      }
    }
    else
    {
      TEST.log(Status.PASS, " || " + text);
    }
  }

  public static void addTestResult(TestExecutionResult result) {
    try {
      switch (result.getStatus()) {
        case FAILED:
          String temp = takeScreenshot(getDriver());
          TEST.log(Status.FAIL, "Test Result: " + Status.FAIL + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>" + "<pre>" + "Stack Trace: " + GeneralUtils.getAllStackTrace(result) + "</pre>",
           MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
          break;
        case ABORTED:
          TEST.log(Status.SKIP, "Test Result: " + Status.SKIP + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>" + "<pre>" + "Stack Trace: " + GeneralUtils.getAllStackTrace(result) + "</pre>");
          break;
        default:
          TEST.log(Status.PASS, "Test Result: " + Status.PASS);
          break;
      }
    } catch (Exception e) {
      TEST.log(Status.FAIL, "Test Result: " + Status.FAIL + "<pre>" + "Message: " + result.getThrowable().toString() + "</pre>" + "<pre>" + "Stack Trace: " + GeneralUtils.getAllStackTrace(result) + "</pre>" );
    }
  }

  public static void generateReport(){
    EXTENT_REPORT.flush();
  }

  public static String takeScreenshot(WebDriver driver)
  {
    TakesScreenshot ts=(TakesScreenshot) driver;
    File src=ts.getScreenshotAs(OutputType.FILE);
    String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
    String path= reportsPath+"Screenshot/"+fileName+".png";
    File destination=new File(path);

    try
    {
      FileUtils.copyFile(src, destination);
    } catch (IOException e)
    {
      System.out.println("Capture Failed "+e.getMessage());
    }
    return path;
  }
}