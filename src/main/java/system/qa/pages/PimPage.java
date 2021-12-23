package system.qa.pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import system.qa.base.BasePage;
import system.qa.util.TestUtil;

public class PimPage extends BasePage {


    By add_employee = By.id("menu_pim_addEmployee");
    By first_name = By.id("firstName");
    By middle_name = By.id("middleName");
    By last_name = By.id("lastName");
    By create_checkbox = By.id("chkLogin");
    By user_name = By.id("user_name");
    By user_password = By.id("user_password");
    By confirm_password = By.id("re_password");
    By status = By.id("status");
    By save = By.id("btnSave");
    By employee_list = By.id("menu_pim_viewEmployeeList");
    boolean element_found=false;


    PimPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean select_addEmployees() {
       return  TestUtil.select_links(add_employee);
    }

    public void create_new_contact(String first_name, String middle_name, String last_name,
                                   String user_name, String user_password, String confirm_password, String option) {
        driver.findElement(this.first_name).sendKeys(first_name);
        driver.findElement(this.middle_name).sendKeys(middle_name);
        driver.findElement(this.last_name).sendKeys(last_name);
        driver.findElement(this.create_checkbox).click();
        driver.findElement(this.user_name).sendKeys(user_name);
        driver.findElement(this.user_password).sendKeys(user_password);
        driver.findElement(this.confirm_password).sendKeys(confirm_password);
        Select select = new Select(driver.findElement(status));
        select.selectByValue(option);
        driver.findElement(this.save).click();
    }

    public boolean verifyAddedData(String fist_name) {
        WebElement first = driver.findElement(By.xpath("(//td/a[contains(text(),'"+fist_name+"')])[1]"));
        WebElement check = driver.findElement(By.xpath(" //a[contains(text(),'"+fist_name+"')]/preceding::input[1]"));

        if (first.isDisplayed() && check.isDisplayed()) {
            check.click();
            element_found=true;
        }
        return element_found;
}





}
