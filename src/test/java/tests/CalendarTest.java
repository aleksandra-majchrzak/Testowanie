package tests;

import dataProviders.CalendarDataProvider;
import dataProviders.EventDataProvider;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mohru on 2016-06-07.
 */
public class CalendarTest extends TestBase {

    @Parameters("browserType")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserType) throws Exception {

    }

    @BeforeMethod(alwaysRun = true)
    public void setUpMainPage() throws Exception {
        driver.get("https://www.google.com/calendar");

    }

    public void openNewCalendarSite() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(mainPage.calendarsListButton));
        mainPage.calendarsOptionButton.click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(mainPage.calendarsOptionMenu));
        Thread.sleep(1000);
        mainPage.calendarsOptionMenuAddNew.click();

        Thread.sleep(1000);
    }
/*
    @Test(priority=2, groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void createCalendarWithoutName() throws InterruptedException {
        openNewCalendarSite();

        driver.findElement(By.id("settings_save_btn")).click();
        Thread.sleep(1000);
        assertEquals(wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cal-dialog")))).isDisplayed(), true);   //tu ewentualnie mozesz porownywac tekst

    }
*/

    @Test(priority=2, groups="chrome", dataProviderClass= CalendarDataProvider.class, dataProvider= "calendarData")
    public void createCalendar(String calendarName, String description, String location, String errorMsg ) throws InterruptedException {

        openNewCalendarSite();

        newCalendarPage.setCalendarName(calendarName);
        newCalendarPage.setCalendarDescription(description);
        newCalendarPage.setCalendarLocation(location);

        newCalendarPage.createCalendarButton.click();

        if (StringUtils.isBlank(errorMsg)) {

            Thread.sleep(1000);

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nt2"))));   // pytanie czy id nie jest generowane jakos dynamicznie

            assertEquals(mainPage.getCalendarRow(driver, calendarName).isDisplayed(), true);
        }
        else{

            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cal-dialog"))));

            assertEquals(driver.findElement(By.cssSelector(".cal-dialog-content")).getText(), errorMsg);   //tu ewentualnie mozesz porownywac tekst
        }


    }

    @Test(priority=2, groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void deleteCalendar() throws InterruptedException {

        String calendarName = "testowy2";

        if(! mainPage.calendarsList.isDisplayed())
            mainPage.calendarsListButton.click();

        wait.until(ExpectedConditions.visibilityOf(mainPage.calendarsList));

        mainPage.openCalendarSettings(driver, calendarName);

        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOf(editCalendarPage.calendarMenuOpionsList));

        editCalendarPage.deleteCalendar();

        // tu jest jeszcze dialog

        //wait.until(ExpectedConditions.);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nt2")) ));
        wait.until(ExpectedConditions.invisibilityOfElementWithText(By.id("nt2"), "Usuwanie kalendarza...") );   // pytanie czy id nie jest generowane jakos dynamicznie

        Assert.assertEquals(driver.getCurrentUrl(), "https://calendar.google.com/calendar/render#main_7");

// problem bo mozesz miec kilka kalendarzy o tej samej nazwie - powinnas jeszcze sprawdzac komunikat o utworzeniu?

    }
}
