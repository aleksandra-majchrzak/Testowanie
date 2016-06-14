package tests;

import dataProviders.EventDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Created by Mohru on 2016-06-07.
 */
public class EventTest  extends TestBase {


    @Parameters("browserType")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserType) throws Exception {

    }

    @BeforeMethod(alwaysRun = true)
    public void setUpMainPage() throws Exception {
        driver.get("https://www.google.com/calendar");

    }

    @Test(priority=3, groups={"chrome", "firefox", "ie", "opera", "safari"}, dataProviderClass= EventDataProvider.class, dataProvider= "eventData", enabled = false)
    public void createEvent(String eventName, String startDate, String startTime, String endDate,
                            String endTime, String location, String calendarName, String description,
                            boolean isAllDay, boolean repeat, int repeatOption, int repeatFrequency,
                            int colorIndex, String errorMsg) throws Exception {

        mainPage.createEvent();
        wait.until(ExpectedConditions.visibilityOf(eventPage.EventTitle));

        eventPage.setEventName(eventName);
        eventPage.setEventDates(startDate, startTime, endDate, endTime);

        //eventPage.addReminder();

        eventPage.setLocation(location);
        eventPage.setCalendar(calendarName);
        eventPage.setDescription(description);
        eventPage.setAllDay(isAllDay);

        if(repeat)
            eventPage.enableRepeatEvent(repeatOption, "", repeatFrequency);

        eventPage.setColor(colorIndex);

        eventPage.saveEvent();

        if (errorMsg == null || errorMsg.isEmpty()) {

            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nt2"))));

            assertTrue(driver.findElement(By.id("nt2")).getText().contains("Dodano wydarzenie"));
        }
        else{

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cal-dialog"))));

            assertEquals(driver.findElement(By.cssSelector(".cal-dialog-content")).getText(), errorMsg);
        }

    }

    @Test(priority=3, groups={"chrome", "firefox", "ie", "opera", "safari"}, dataProviderClass= EventDataProvider.class, dataProvider= "quickEventData")
    public void quickCreateEvent(String time, String eventTitle, String calendarName) throws Exception{

        mainPage.DayView.click();
        mainPage.quickCreateEvent(time, eventTitle, calendarName, driver);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nt2"))));

        mainPage.checkEvent(time, eventTitle, calendarName, driver);
    }

    @Test(priority=3, groups={"chrome", "firefox", "ie", "opera", "safari"}, dataProviderClass= EventDataProvider.class, dataProvider= "quickEventData")
    public void quickDeleteEvent(String time, String eventTitle, String calendarName) throws Exception{

        mainPage.DayView.click();
        mainPage.quickDeleteEvent(time, eventTitle, calendarName, driver);

        Thread.sleep(100);
        mainPage.checkEventDeleted(time, eventTitle, calendarName, driver);
    }
}
