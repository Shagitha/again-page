package systaem.qa.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.*;
import system.qa.ExtentReport.GenerateExtentReport;
import system.qa.base.BasePage;
import system.qa.pages.AdminPage;
import system.qa.pages.DashboardPage;
import system.qa.pages.LoginPage;
import system.qa.pages.PimPage;
import system.qa.util.TestUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PimPageTest extends BasePage {

    LoginPage l;
    PimPage pim;
    DashboardPage dash;


    PimPageTest(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        GenerateExtentReport.log=GenerateExtentReport.report.createTest("This is Pim page test");
        initialization();
        GenerateExtentReport.log.createNode("Browser navigated to orange demo site");
        l=new LoginPage();
        dash= l.input(prop.getProperty("username"), prop.getProperty("password"));
        GenerateExtentReport.log.createNode("login to the page successfully");
        pim= dash.clickPim();
    }
    @DataProvider
    public Object[][] getTestData(){
        Object data[][]=TestUtil.getTestDataExcel("Add_Employee");
        return data;
    }
    @Test(dataProvider = "getTestData")
    public void add_Data(String first_name,String middle_name,String last_name,
                         String user_name,String user_password,String confirm_password,String option){
        GenerateExtentReport.log=GenerateExtentReport.report.createTest("Verify data can be added in pim page");
        Assert.assertTrue(pim.select_addEmployees(),"Add_Employee option missing in the page");
        pim.create_new_contact(first_name,middle_name,last_name,user_name,user_password,confirm_password,option);
       GenerateExtentReport.log.createNode(first_name+" data successfully added");
       // pim.create_new_contact("test","test2","test3","test4","test5","test6","Enabled");
    }
    @Test
    public void verify_addedData(){
        Assert.assertTrue(pim.verifyAddedData("saji"),"element not found");
        GenerateExtentReport.log.createNode("data successfully verified");
    }


    @AfterMethod
    public void close(){
       driver.quit();
    }


}
