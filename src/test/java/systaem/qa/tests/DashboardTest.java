package systaem.qa.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import system.qa.ExtentReport.GenerateExtentReport;
import system.qa.base.BasePage;
import system.qa.pages.AdminPage;
import system.qa.pages.DashboardPage;
import system.qa.pages.LoginPage;
import system.qa.pages.PimPage;
import com.aventstack.extentreports.ExtentReports;

import java.util.Iterator;

public class DashboardTest extends BasePage {
    DashboardPage dash;
    AdminPage admin;
    PimPage pim;
   /* ExtentReports reports =new ExtentReports();
    ExtentTest test=reports.createTest("DashBoard Page");*/
    DashboardTest(){
            super();
        }

    @BeforeMethod
    public void setUp(){
        GenerateExtentReport.log=GenerateExtentReport.report.createTest("This is Dashboard test");
        initialization();
        GenerateExtentReport.log.createNode("Browser navigated to orange demo site");
        l=new LoginPage();
        GenerateExtentReport.log.createNode("login to the page successfully");
        dash=l.input(prop.getProperty("username"), prop.getProperty("password"));

    }
    @Test
    public void clickAdmin(){
        GenerateExtentReport.log=GenerateExtentReport.report.createTest("Verify text is displayed in admin page");
        System.out.println("user logged in is: "+dash.userLogged());
        admin=dash.clickAdmin();
        GenerateExtentReport.log.createNode("Browser displayed admin page");
    }
    @Test
    public void clickPim(){
        GenerateExtentReport.log.createNode("Verify navigating to  pim page page");
        System.out.println("user logged in is: "+dash.userLogged());
        pim=dash.clickPim();
        GenerateExtentReport.log.createNode("Browser navigated to pim page");
    }

    @Test
    public void chekQuickLaunch(){
        dash.quick_launch_text();
        dash.quick_launch_clickLinks();
        GenerateExtentReport.log.createNode("Browser verified links and launch texts");
    }
    @Test
    public void check_secondQuote(){
            dash.getText();
            dash.get_Second_Text();
            dash.get_third_text();
        GenerateExtentReport.log.createNode("Browser verified each text =");
    }
    public void check_hover(){
            dash.hover_over();
    }
    @AfterMethod
    public void close(){
        driver.quit();
    }

}
