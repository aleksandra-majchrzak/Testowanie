package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
}
