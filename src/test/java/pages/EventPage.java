package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import  java.util.List;

import static tests.TestBase.wait;

/**
 * Created by Mohru on 13.06.2016.
 */
public class EventPage {


    @FindBy (css="[class^='textinput'][type='text']") public WebElement EventTitle;
    @FindBy (css="[id$='-sd'][class^='text dr-date']") public WebElement StartDate;
    @FindBy (css="[id$='-ed'][class^='text dr-date']") public WebElement EndDate;
    @FindBy (css="[id$='-st'][class^='text dr-time']") public WebElement StartTime;
    @FindBy (css="[id$='-et'][class^='text dr-time']") public WebElement EndTime;
    @FindBy (css=".goog-container.goog-container-vertical") public WebElement SelectTime;
    @FindBy (css="[id$='location-label']") public WebElement Label;
    @FindBy (css="[id$='repeatcheckbox']") public WebElement RepeatCheckbox;
    @FindBy (xpath=".//*[@id='coverinner']/div/div[3]/div[1]/div/span[2]/span/input") public WebElement allDayCheckbox;
    @FindBy (css="[id$='frequency']") public WebElement Frequency;
    @FindBy (css="[id$='endson_count']") public WebElement CountCheck;
    @FindBy (css="[id$='endson_count_input") public WebElement CountInput;
    @FindBy (css="[id$='repeatsummary']") public WebElement Summary;
    @FindBy (css=".ep-rec-buttons-padding>div>div:first-child") public WebElement DoneButton;
    @FindBy (css="input[class='textinput'][type='text'][autocomplete='off']") public WebElement Location;
    @FindBy (css="[class$='calendar-sel']") public WebElement SelectCalendar;
    @FindBy (css="textarea") public WebElement Description;
    @FindBy (css="[id$='save_top']>div>div>div>div") public WebElement SaveButton;
    @FindBy (css=".gcal-reminderlist > span") public WebElement NewReminder;

    private WebDriver driver;

    public void setDriver(WebDriver driver){
        this.driver = driver;
    }


    public void setEventName(String title) throws Exception{
        setText(EventTitle, title);
    }

    public void setEventDates(String startDate, String startTime, String endDate, String endTime) throws Exception{
        wait.until(ExpectedConditions.visibilityOf(StartDate));
        setText(StartDate, startDate);
        StartTime.click();

        if(!selectValueFromList(SelectTime, startTime))
            setText(StartTime, startTime);

        setText(EndDate, endDate);
        EndTime.click();

        if(!selectValueFromList(SelectTime, endTime))
            setText(EndTime, endTime);

    }

    public void setAllDay(boolean isAllDay){

        if(isAllDay && ! allDayCheckbox.isSelected())
            allDayCheckbox.click();
        else if(allDayCheckbox.isSelected())
            allDayCheckbox.click();

    }

    public void enableRepeatEvent(int frequencyOption, String text, int frequency) throws Exception{

        if (!RepeatCheckbox.isSelected())
            RepeatCheckbox.click();

        new Select(Frequency).selectByIndex(frequencyOption);

        if (!CountCheck.isSelected())
            CountCheck.click();

        setText(CountInput,String.valueOf(frequency));
        driver.findElement(By.cssSelector("[id$='endson_until_input']")).click();
        DoneButton.click();
        Assert.assertTrue(RepeatCheckbox.isSelected());
    }

    public void setLocation(String text) throws Exception{

        Location.sendKeys(text);
        Thread.sleep(500);

        Actions action = new Actions(driver);
        action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();

    }

    public void setCalendar(String text) throws Exception{

        new Select(SelectCalendar).selectByVisibleText(text);

    }

    public void setDescription(String text) throws Exception{

        setText(Description, text);
    }

    public void setColor(int index) throws Exception{

        WebElement color = driver.findElement(By.xpath("//div[contains(@id, eventColors)]/div/div[@role ='radio']["+index+"]"));
        color.click();
    }

    public void addReminder(int count) throws Exception{

        for (int i=0; i<count; i++) {
            NewReminder.click();
        }
    }

    public void editReminder(String number, String value, String time, String value2) throws Exception{

        String reminder = ".gcal-reminderlist > div >div:nth-child("+number+")";
        new Select(driver.findElement(By.cssSelector(reminder+"> select"))).selectByValue(value);
        new Select(driver.findElement(By.cssSelector(reminder+" > span > span > select"))).selectByValue(value2);
        setText(driver.findElement(By.cssSelector(reminder+" > span > span > input")), time);
    }

    public void saveEvent() throws Exception{

        SaveButton.click();
    }


    public boolean selectValueFromList(WebElement List, String value) {
        List<WebElement> options = List.findElements(By.className("goog-control"));

        for (WebElement option : options) {
            if (option.isDisplayed() && value.equals(option.getText())) {
                option.click();
                return true;
            }
        }

        return false;
    }

    public void setText(WebElement element, String text)throws Exception{

        element.clear();
        element.sendKeys(text);
        //Assert.assertEquals(element.getAttribute("value"), text);
    }
}
