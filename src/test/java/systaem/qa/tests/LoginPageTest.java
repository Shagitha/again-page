package systaem.qa.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import system.qa.ExtentReport.GenerateExtentReport;
import system.qa.base.BasePage;
import system.qa.pages.DashboardPage;
import system.qa.pages.LoginPage;

public class LoginPageTest extends BasePage {

    LoginPage l;
    DashboardPage dash;
    GenerateExtentReport getReport;


    LoginPageTest(){
        super();
    }

    @BeforeTest
    public void startReport(){
        GenerateExtentReport.createReportTheme((getClass().getName()),"ExtentReport");
    }

    @BeforeMethod
    public void setUp(){
        GenerateExtentReport.log=GenerateExtentReport.report.createTest("Verify URL and browser");
       initialization();
        GenerateExtentReport.log.createNode("Browser navigated to respective page");
       l=new LoginPage();
        GenerateExtentReport.log.createNode("Navigated to login page");
    }
    @Test
    public void test(){
       // l=new LoginPage();
        GenerateExtentReport.log=GenerateExtentReport.report.createTest("Verify Login Page");
        String title=  l.verifyTitle();
        Assert.assertEquals(title,"OrangeHRM");
        GenerateExtentReport.log.createNode("Navigated to correct page");
        Assert.assertTrue(l.logo());
        GenerateExtentReport.log.createNode("logo displayed");
        dash= l.input(prop.getProperty("username"), prop.getProperty("password"));
        GenerateExtentReport.log.createNode("User name and Password Entered");
        //GenerateExtentReport.log.getExtent();
    }
    @AfterMethod
    public void close(){
        driver.quit();

    }
    @AfterTest
    public void endReport(){
        GenerateExtentReport.report.flush();
    }


}
