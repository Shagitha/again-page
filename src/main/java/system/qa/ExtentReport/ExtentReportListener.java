package system.qa.ExtentReport;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.IOException;

public class ExtentReportListener implements ITestListener {

    long startTime;
    long endTime;
    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Test started--->"+iTestResult.getName());

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Test successfully executed--->"+iTestResult.getName());
        GenerateExtentReport.getResult(iTestResult);

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Test failed-->"+iTestResult.getName());
        GenerateExtentReport.getResult(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("Test skipped--->"+iTestResult.getName());
        GenerateExtentReport.getResult(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("Test executed only few part--->"+iTestResult.getName());
        GenerateExtentReport.getResult(iTestResult);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("Execution started--->"+iTestContext.getName());
        startTime=System.currentTimeMillis();
        System.out.println("start time is"+startTime);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("Test finished"+iTestContext.getName());
        endTime=System.currentTimeMillis();
        System.out.println("End time is"+endTime);
        System.out.println("Time Taken:- "+((endTime-startTime)/1000));


    }
}
