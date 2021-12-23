package system.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import system.qa.base.BasePage;
import java.util.List;

public class DashboardPage extends BasePage {


    By user = By.cssSelector("a#welcome");
    By admin = By.id("menu_admin_viewAdminModule");
    By pim = By.id("menu_pim_viewPimModule");


    @FindBy(css = "div.head h1")
    WebElement page_head;

    @FindBy(xpath = "//fieldset[@id=\"panel_resizable_0_0\"]/legend")
    WebElement quick_launch;


    By quick_launch_links = By.xpath("//table[@class=\"quickLaungeContainer\"]//td//a");
    By quick_launch_links_text = By.xpath("//table[@class=\"quickLaungeContainer\"]//td//a//span");
    By first_table=By.id("panel_draggable_1_0");
    By second_table=By.id("panel_draggable_1_1");
    By third_table=By.id("panel_draggable_1_2");
    By third_table_link1=By.className("odd");
    By third_table_link2=By.className("even");

    WebDriverWait wait;
    //to initiate elements
    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }

    //get links texts in quick launch table
    public void quick_launch_text() {
        List<WebElement> list = driver.findElements(quick_launch_links_text);
        for (WebElement web : list) {
            wait = new WebDriverWait(driver, 20);
            try {
                System.out.println(web.getText());
                // driver.navigate().refresh();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    //click on links in quick_launch table and move to the respective page
    public void quick_launch_clickLinks() {
        List<WebElement> list = driver.findElements(quick_launch_links);
        try {
            for (int i = 0; i < list.size(); i++) {
                wait.until(ExpectedConditions.presenceOfElementLocated(quick_launch_links));
                System.out.println("Clicked on link ----> "+i+" "+driver.findElements(quick_launch_links_text).get(i).getText());
                driver.findElements(quick_launch_links).get(i).click();
                driver.navigate().back();
                wait.until(ExpectedConditions.visibilityOf(page_head));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //Get 3 fieldsets text
    public String getText(){
        return  driver.findElement(first_table).getText();
    }
    public String get_Second_Text(){
        return  driver.findElement(second_table).getText();
    }
    public String get_third_text(){
        return  driver.findElement(third_table).getText();
    }

    //Hover over links
    public void hover_over(){
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(third_table_link1));
        action.moveToElement(driver.findElement(third_table_link2));
    }

    public String userLogged(){
        return driver.findElement(user).getText();
    }
    public AdminPage clickAdmin(){
        driver.findElement(admin).click();
        return new AdminPage();
    }
    public PimPage clickPim(){
        driver.findElement(pim).click();
        return new PimPage();
    }


}
