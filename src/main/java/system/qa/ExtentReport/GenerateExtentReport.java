package system.qa.ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.*;
import system.qa.util.TestUtil;

import java.io.File;
import java.io.IOException;


public class GenerateExtentReport  {

    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports report;
    public static ExtentTest log;

    public static void attachSystemInfor(){

        report.setSystemInfo("Environment","test");
        report.setSystemInfo("Host Name","Saji Machine");
    }
    public static ExtentReports createReportTheme(String ReportName,String DocumentTitle){
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/report.html");
        htmlReporter.config().setReportName(ReportName);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle(DocumentTitle);
        report = new ExtentReports();
        attachSystemInfor();
        report.attachReporter(htmlReporter);
        return  report;
    }


    public static void getResult(ITestResult result)  {
        if(result.getStatus()==ITestResult.SUCCESS){
            log.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" ----Test Case Passed----", ExtentColor.GREEN));
            try {
                String screenshot= TestUtil.takeScreenShotAtError();
                log.pass("Snapshot of testcase is -->"+log.addScreenCaptureFromPath(screenshot));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(result.getStatus()==ITestResult.FAILURE){
            log.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" ----Test Case Failed----", ExtentColor.RED));
            try {
                String Screenshot= TestUtil.takeScreenShotAtError();
                log.fail("Snapshot of testcase is -->"+log.addScreenCaptureFromPath(Screenshot));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if(result.getStatus()==ITestResult.SKIP){
            log.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"----TestCase Skipped----",ExtentColor.ORANGE));
            try {
                String Screenshot= TestUtil.takeScreenShotAtError();
                log.skip("Snapshot of testcase is -->"+log.addScreenCaptureFromPath(Screenshot));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
