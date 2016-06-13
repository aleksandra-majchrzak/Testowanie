package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Created by Mohru on 12.06.2016.
 */
public class NewCalendarPage {

    @FindBy(id="cnInput") public WebElement calendarName;
    @FindBy(id="descriptionInput") public WebElement calendarDescription;
    @FindBy(id="locationInput") public WebElement calendarLocation;
    @FindBy(id="settings_save_btn") public WebElement createCalendarButton;

    public void setText(WebElement element, String text){

        element.clear();
        element.sendKeys(text);
        Assert.assertEquals(element.getAttribute("value"), text);
    }

    public void click(WebElement element){
        element.click();
    }

    public void setCalendarName(String calendarNameString){
        setText(calendarName,calendarNameString);
    }

    public void setCalendarDescription(String calendarDescriptionString){
        setText(calendarDescription,calendarDescriptionString);
    }

    public void setCalendarLocation(String calendarLocationString){
        setText(calendarLocation,calendarLocationString);
    }
}
