package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.TestBase;

import java.io.File;

import  java.util.List;

import static tests.TestBase.wait;

/**
 * Created by Mohru on 11.06.2016.
 */
public class MainPage {

    @FindBy(id="lhscalinner_my") public WebElement calendarsList;
    @FindBy (id="clst_my") public WebElement calendarsListButton;
    @FindBy (id="clst_my_menu") public WebElement calendarsOptionButton;
    @FindBy (id="addP") public WebElement calendarsOptionMenu;
    //@FindBy (xpath="//div[contains(@class, 'goog-menuitem-content')][1]") public WebElement calendarsOptionMenuAddNew;
    @FindBy (xpath="//div[contains(@id, 'addP')]/div[contains(@class, 'goog-menuitem')][1]") public WebElement calendarsOptionMenuAddNew;
    @FindBy (id="cal-list-menu-popup") public WebElement calendarOptionMenu;
    @FindBy (xpath="//*[@id='cal-list-menu-popup']/div/table/tbody/tr/td/div/ul/li[6]") public WebElement calendarOptionMenuEdit;

    @FindBy (xpath="//div[contains(@id, 'addP')]/div[contains(@class, 'goog-menuitem')][2]") public WebElement  calendarsOptionMenuSettings;

    @FindBy(xpath=".//*[@id='sidebar']/div/div/div/div/div/div[2]") public WebElement CreateButton;

    @FindBy (xpath="//*[@id='topRightNavigation']/div[1]/div[contains(@class, 'goog-imageless-button')][2]") public WebElement WeekView;
    @FindBy (xpath="//*[@id='topRightNavigation']/div[1]/div[contains(@class, 'goog-imageless-button')][1]") public WebElement DayView;
    //DayView
    @FindBy (id="tgCol0") public WebElement CalendarField;
    @FindBy (css="[class^='tab-label']") public WebElement EventLabel;
    @FindBy (css="[class$='textinput']") public WebElement EventTitle;
    @FindBy (css=".split-tile-right > div > div > div:nth-child(2)") public WebElement EventDialogCreateButton;
    @FindBy (css=".gcalOptionList") public WebElement SetCalendar;
    @FindBy (css=".gcalOptionMenu.goog-menu") public WebElement SelectCalendar;

    @FindBy (css="#mtb > div > div") public WebElement AddedEventTitle;
    @FindBy (css=".bubbleclose") public WebElement CloseAddedEventButton;
    @FindBy (css=".neb-data>tbody>tr:nth-child(1)>td>span") public WebElement AddedEventCalendar;
    @FindBy (xpath="//div[@class='neb-footer']/span[2]/div[contains(@class, 'delete-button')]") public WebElement DeleteAddedEventButton;


    public void click(WebElement element){
        element.click();
    }

    public WebElement getCalendarRow(WebDriver driver, String calendarName){
        return driver.findElement(By.xpath("//div[contains(@class, 'calListLabel')]/span[text() = '" + calendarName+"']"));
    }

    public WebElement getCalendarRowMenuButton(WebDriver driver, String calendarName){
        return driver.findElement(By.xpath("//div[contains(@class, 'calListLabel')]/span[text() = '" + calendarName+"']/parent::div/parent::div/parent::div/following-sibling::div"));
    }

    public void openCalendarSettings(WebDriver driver, String calendarName){

        WebElement hoverElement = getCalendarRow(driver, calendarName);

        Actions builder = new Actions(driver);
        builder.moveToElement(hoverElement).perform();
        builder.moveToElement(getCalendarRowMenuButton(driver, calendarName))
                .click()
                .perform();

        TestBase.wait.until(ExpectedConditions.visibilityOf(calendarOptionMenu));

        calendarOptionMenuEdit.click(); // zalezne od przegladarki
    }

    public void openCalendarsSettings() throws Exception{

        calendarsOptionButton.click();
        TestBase.wait.until(ExpectedConditions.visibilityOf(calendarsOptionMenu));
        calendarsOptionMenuSettings.click();
    }


    public void createEvent(){

        TestBase.wait.until(ExpectedConditions.visibilityOf(CreateButton));
        CreateButton.click();
    }

    //DayView
    public void quickCreateEvent(String time, String title, String CalendarName, WebDriver driver) throws Exception{

        int i = Integer.parseInt(time.substring(0,2))+1;
        int j = Integer.parseInt(time.substring(3,4));

        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(".tg-hourmarkers > div:nth-child("+i+") > div"));
        int x = driver.findElement(By.id("tgCol0")).getSize().width-2;

        System.out.println(driver.findElement(By.id("tgCol0")).getSize());
        System.out.println(element.getSize());
        System.out.println( element.getLocation().getX());
        System.out.println( x - element.getLocation().getX());


        if (j>0) builder.moveToElement(element , x - element.getLocation().getX() , 20 ).click().build().perform();
        else builder.moveToElement(element , x - element.getLocation().getX() , 5 ).click().build().perform();

        wait.until(ExpectedConditions.visibilityOf(EventLabel));
        setText(EventTitle, title);

        if (SetCalendar.isDisplayed())
            if (!(CalendarName == null || CalendarName.isEmpty())){
                SetCalendar.click();
                selectValueFromList(SelectCalendar, CalendarName);}

        EventDialogCreateButton.click();
    }

    public void setText(WebElement element, String text)throws Exception{

        element.clear();
        element.sendKeys(text);
        Assert.assertEquals(element.getAttribute("value"), text);
    }

    public void selectValueFromList(WebElement List, String value) {

        List<WebElement> options = List.findElements(By.className("gcalOption"));

        for (WebElement option : options) {
            if (value.equals(option.getAttribute("aria-label"))) {
                option.click();
                break;
            }
        }
    }

    public void checkEvent(String time, String title, String CalendarName, WebDriver driver) throws Exception{

        driver.findElement(By.id("mainlogo")).click();
        Thread.sleep(500);
        int i = Integer.parseInt(time.substring(0,2))+1;
        int j = Integer.parseInt(time.substring(3,4));
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(".tg-hourmarkers > div:nth-child("+i+") > div"));

        int x = driver.findElement(By.id("tgCol0")).getSize().width-2;

        if (j>0) builder.moveToElement(element , x - element.getLocation().getX(), 21 ).click().build().perform();
        else builder.moveToElement(element ,  x - element.getLocation().getX() , 6 ).click().build().perform();

        wait.until(ExpectedConditions.visibilityOf(AddedEventTitle));
        Assert.assertEquals(AddedEventTitle.getText(), title.equals("") ? "(Bez tytułu)" : title);

        if (AddedEventTitle.isDisplayed())
            if (!(CalendarName == null || CalendarName.isEmpty()))
                Assert.assertEquals(AddedEventCalendar.getText(), CalendarName);

        CloseAddedEventButton.click();
    }

    //DayView
    public void quickDeleteEvent(String time, String title, String CalendarName, WebDriver driver) throws Exception{

        int i = Integer.parseInt(time.substring(0,2))+1;
        int j = Integer.parseInt(time.substring(3,4));

        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(".tg-hourmarkers > div:nth-child("+i+") > div"));

        System.out.println(driver.findElement(By.id("tgCol0")).getSize());

        if (j>0) builder.moveToElement(element, 5 , 21).click().build().perform();
        else builder.moveToElement(element, 5 , 6).click().build().perform();


        wait.until(ExpectedConditions.visibilityOf(AddedEventTitle));

        if (AddedEventTitle.isDisplayed())
            if (!(CalendarName == null || CalendarName.isEmpty()))
                DeleteAddedEventButton.click();
    }

    public void checkEventDeleted(String time, String title, String CalendarName, WebDriver driver) throws Exception{

        driver.findElement(By.id("mainlogo")).click();
        Thread.sleep(500);
        int i = Integer.parseInt(time.substring(0,2))+1;
        int j = Integer.parseInt(time.substring(3,4));
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(By.cssSelector(".tg-hourmarkers > div:nth-child("+i+") > div"));

        int x = driver.findElement(By.id("tgCol0")).getSize().width-2;

        if (j>0) builder.moveToElement(element , x - element.getLocation().getX(), 21 ).click().build().perform();
        else builder.moveToElement(element ,  x - element.getLocation().getX() , 6 ).click().build().perform();

        wait.until(ExpectedConditions.visibilityOf(CloseAddedEventButton));
        boolean isEventDeleted = driver.findElements(By.cssSelector("#mtb > div > div")).size() == 0
                                    || ! AddedEventTitle.getText().equals(title.equals("") ? "(Bez tytułu)" : title);

        Assert.assertTrue(isEventDeleted);

     //   if (AddedEventTitle.isDisplayed())
    //        if (!(CalendarName == null || CalendarName.isEmpty()))
     //           Assert.assertEquals(AddedEventCalendar.getText(), CalendarName);

        CloseAddedEventButton.click();
    }
}
