package system.qa.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import system.qa.base.BasePage;

public class LoginPage extends BasePage {


    By username= By.name("txtUsername");
    By password=By.name("txtPassword");
    By login=By.cssSelector("div#divLoginButton  input#btnLogin");
    By logo=By.cssSelector("div#divLogo img");

    /*@FindBy(name="txtUsername")
    WebElement username;
    @FindBy(name="txtPassword")
    WebElement password;
    @FindBy(xpath="div#LoginButton  input#btnLogin")
    WebElement login;
    @FindBy(name="div#divLogo img")
    WebElement logo;*/

    //initializing page objects
    public LoginPage(){
       PageFactory.initElements(driver,this);

    }

    public String verifyTitle(){
        return driver.getTitle();
    }
    public boolean logo(){
        return driver.findElement(logo).isDisplayed();
    }

    public DashboardPage input(String user,String pass){
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(login).click();

        return new DashboardPage();
    }




}
