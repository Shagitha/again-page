package system.qa.pages;

import org.openqa.selenium.By;
import system.qa.base.BasePage;

public class AdminPage extends BasePage {

    By verify_text= By.xpath("//h1[contains(text(),\"System Users\")]");

    public String testDisplayed(){
        return driver.findElement(verify_text).getText();
    }
}
