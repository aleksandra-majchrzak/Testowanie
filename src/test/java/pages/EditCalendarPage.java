package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.TestBase;

import java.io.File;

/**
 * Created by Mohru on 12.06.2016.
 */
public class EditCalendarPage {

    @FindBy(id="svalues") public WebElement calendarMenuOpionsList;
    @FindBy(xpath="//*[@id='svalues']/tbody/tr[11]/td/div[1]/a") public WebElement deleteCalendarLink;
    @FindBy(id="se-dd-cb") public WebElement deleteCalendarConfirmationButton;
    @FindBy(name="di_delete") public WebElement deleteCalendarButton;

    public void click(WebElement element){
        element.click();
    }

    public void deleteCalendar(){

        deleteCalendarLink.click();

        TestBase.wait.until(ExpectedConditions.visibilityOf(deleteCalendarConfirmationButton));

        deleteCalendarConfirmationButton.click();
        deleteCalendarButton.click();
    }

    public boolean isFileExported(String exportPath, String fileName) {

        File dir = new File(exportPath);
        File[] dir_contents = dir.listFiles();

        if(dir_contents == null)
            return false;

        for (int i=0; i<dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(fileName))
                return true;
        }

        return false;
    }
}
