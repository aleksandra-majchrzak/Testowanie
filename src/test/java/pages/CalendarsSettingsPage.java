package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import tests.TestBase;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Mohru on 13.06.2016.
 */
public class CalendarsSettingsPage {

    @FindBy(xpath=".//*[@id='calSettingsWrapper']/table/tbody[2]/tr/td/table/tbody/tr/td[1]/nobr/a[1]") public WebElement importCalendarLink;
    @FindBy (xpath="//html/body/div[@class='static-form-dialog']/div[2]/form/table/tbody/tr[1]/td/div[1]/input[@class='gc-dialoginput']") public WebElement selectImportDirectoryButton;
    @FindBy (xpath="//html/body/div[@class='static-form-dialog']/div[2]/form/table/tbody/tr[3]/td/div[1]/select") public WebElement importCalendarSelect;
    @FindBy (xpath="//html/body/div[@class='static-form-dialog']/div[2]/form/table/tbody/tr[3]/td/div[@class='gc-dialogrow']/button[@class='gc-dialogbold']") public WebElement importCalendarButton;
    @FindBy (xpath="//html/body/div[@class='static-form-dialog']/div[2]/p") public WebElement Info;
    @FindBy (xpath="//html/body/div[@class='static-form-dialog']/div[2]/form/button") public WebElement FinishButton;

    public void startImport() throws Exception{

        TestBase.wait.until(ExpectedConditions.visibilityOf(importCalendarLink));
        importCalendarLink.click();
        TestBase.wait.until(ExpectedConditions.visibilityOf(selectImportDirectoryButton));
        selectImportDirectoryButton.click();
        Thread.sleep(2000);

    }

    public void finishImport(String calendarName, String info) throws Exception{

        new Select(importCalendarSelect).selectByVisibleText(calendarName);
        importCalendarButton.click();
        Assert.assertEquals(Info.getText(), info);
        FinishButton.click();
        //driver.get("https://www.google.com/calendar");

    }

    public void insertFileName(String fileName) throws Exception{

        Robot robot = new Robot();

        for(int i = 0; i <  fileName.length(); ++i){
            robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(fileName.charAt(i)));
        }

        robot.keyPress(KeyEvent.VK_PERIOD);
        robot.keyPress(KeyEvent.VK_I);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyPress(KeyEvent.VK_S);
        robot.keyPress(KeyEvent.VK_ENTER);
    }
}
