package tests;

import dataProviders.EventDataProvider;
import org.apache.commons.lang3.StringUtils;
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

    @Test(priority=3, groups="chrome", dataProviderClass= EventDataProvider.class, dataProvider= "eventData")
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

        if (StringUtils.isBlank(errorMsg)) {

            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nt2"))));   // pytanie czy id nie jest generowane jakos dynamicznie

            assertTrue(driver.findElement(By.id("nt2")).getText().contains("Dodano wydarzenie"));
        }
        else{

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cal-dialog"))));

            assertEquals(driver.findElement(By.cssSelector(".cal-dialog-content")).getText(), errorMsg);   //tu ewentualnie mozesz porownywac tekst
        }

    }

}
