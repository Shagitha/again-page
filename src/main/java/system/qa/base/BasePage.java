package system.qa.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import system.qa.pages.LoginPage;
import system.qa.util.TestUtil;
import system.qa.util.WebEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BasePage {
    protected static Properties prop;
    protected static WebDriver driver;
    protected LoginPage l;

    public BasePage(){
        try{
            prop=new Properties();
            FileInputStream file=new FileInputStream(System.getProperty("user.dir")+
                    "/src/main/java/system/qa/config/properties");
            prop.load(file);
        }catch(FileNotFoundException fe){
            fe.printStackTrace();
        }catch(IOException ie){
            ie.printStackTrace();
        }

    }
    public static void initialization(){
        String browser_name=prop.getProperty("browser");
        String username=prop.getProperty("username");
        String password=prop.getProperty("password");

        if(browser_name.equals("chrome")){
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
            driver=new ChromeDriver();
        }
        EventFiringWebDriver e_driver=new EventFiringWebDriver(driver);
        //create object of the WebDriverEventListener interface
        WebEventListener eventListner=new WebEventListener();
        //register with EventFiringWeb driver
        e_driver.register(eventListner);
        driver=e_driver;


        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
        //return new LoginPage();
    }
}
