package systaem.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import system.qa.ExtentReport.GenerateExtentReport;
import system.qa.base.BasePage;
import system.qa.pages.AdminPage;
import system.qa.pages.DashboardPage;
import system.qa.pages.LoginPage;

public class AdminTest extends BasePage {
    LoginPage l;
    AdminPage admin;
    DashboardPage dash;

    AdminTest(){
        super();
    }


    @BeforeMethod
    public void setUp(){
        GenerateExtentReport.log=GenerateExtentReport.report.createTest("Verify Admin test link clickable");
        initialization();
        GenerateExtentReport.log.createNode("Browser navigated to respective page");
        l=new LoginPage();
        GenerateExtentReport.log.createNode("Navigated to login page");
        dash= l.input(prop.getProperty("username"), prop.getProperty("password"));
        GenerateExtentReport.log.createNode("Navigated to dashboard page");
        admin= dash.clickAdmin();
    }
    @Test
    public void test(){
        GenerateExtentReport.log=GenerateExtentReport.report.createTest("Verify text is displayed in admin page");
        Assert.assertEquals(admin.testDisplayed(),"System Users","text not displayed in admin Page");
        GenerateExtentReport.log.createNode("Browser displayed admin page");
    }
    @AfterMethod
    public void close(){
        driver.quit();
    }

}
