package system.qa.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openxml4j.exceptions.InvalidFormatException;
import system.qa.base.BasePage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestUtil extends BasePage {

    public static String TEST_SHEET_DATA_PATH=System.getProperty("user.dir") +
            "/src/main/java/system/qa/testdata/EMployee_test_data.xlsx";
    static Workbook book;
    static Sheet sheet;

    public static Object[][] getTestDataExcel(String sheetName){
        FileInputStream file=null;
        try{
            file=new FileInputStream(TEST_SHEET_DATA_PATH);

        }catch(FileNotFoundException f){
           f.printStackTrace();
        }try{
            String x=TEST_SHEET_DATA_PATH.substring(TEST_SHEET_DATA_PATH.indexOf("."));
           // System.out.println(x);
            if(x.equals(".xlsx"))
                book=new XSSFWorkbook(file);
            if(x.equals(".xlx"))
                book=new HSSFWorkbook(file);

        }catch(IOException e){
            e.printStackTrace();
        }
        sheet=book.getSheet(sheetName);
        int row_count=sheet.getLastRowNum();
        int column_count=sheet.getRow(0).getLastCellNum();
        Object [][] data=new Object[row_count][column_count];
        for(int i=0;i<row_count;i++){
            for(int j=0;j<column_count;j++){
                data[i][j]=sheet.getRow(i+1).getCell(j).toString();
            }
           // System.out.println(data);
        }
        return data;
    }
    public static String takeScreenShotAtError() throws IOException, WebDriverException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String currentDir = (System.getProperty("user.dir")+"/screenshots/" +System.currentTimeMillis() +  ".png");
        //copy the screenshot in destination directory
        //  FileHandler.copy(scrFile, new File(currentDir + "/screenshots/"+getClass().getName() +" "+System.currentTimeMillis() + ".png"));
        FileUtils.copyFile(scrFile, new File(currentDir ));

        return currentDir;
}
    public static boolean select_links(By element) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(element)).build().perform();
        driver.findElement(element).click();

        return driver.findElement(element).isDisplayed();
    }



}
