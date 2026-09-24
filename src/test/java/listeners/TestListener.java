package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        new File("report").mkdirs();
        ExtentSparkReporter spark = new ExtentSparkReporter("report/ExtentReport.html");
        spark.config().setReportName("OrangeHRM Login Automation Report");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @Override
    public void onTestStart(ITestResult result) {
        Object[] params = result.getParameters();
        String name = result.getMethod().getMethodName();
        if (params != null && params.length >= 3) {
            name += " [User: '" + params[0] + "' | Expected: '" + params[2] + "']";
        }
        test.set(extent.createTest(name));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String base64 = ((BaseTest) result.getInstance()).getScreenshot();
        test.get().pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String base64 = ((BaseTest) result.getInstance()).getScreenshot();
        test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush();
        }
    }
}
