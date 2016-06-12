
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mohru on 2016-06-07.
 */
public class CalendarTest extends BaseTest{

    @Parameters("browserType")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserType) throws Exception {
      //  super.setUp(browserType);
 //       driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);


    }

    @BeforeMethod(alwaysRun = true)
    public void setUpMainPage() throws Exception {
        driver.get("https://www.google.com/calendar");

    }

    public void openNewCalendarSite() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(mainPage.calendarsListButton));
        mainPage.click(mainPage.calendarsOptionButton);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(mainPage.calendarsOptionMenu));
        Thread.sleep(1000);
        mainPage.click(mainPage.calendarsOptionMenuAddNew);

        Thread.sleep(1000);
    }

    @Test(priority=2, groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void createCalendarWithoutName() throws InterruptedException {
        openNewCalendarSite();

        driver.findElement(By.id("settings_save_btn")).click();
        Thread.sleep(1000);
        assertEquals(wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cal-dialog")))).isDisplayed(), true);   //tu ewentualnie mozesz porownywac tekst

    }

    @Test(priority=2, groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void createCalendarWithName() throws InterruptedException {
        openNewCalendarSite();

        String calendarName = "testowy";

        newCalendarPage.setCalendarName(calendarName);
        newCalendarPage.click(newCalendarPage.createCalendarButton);

        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nt2"))));   // pytanie czy id nie jest generowane jakos dynamicznie

        assertEquals(mainPage.getCalendarRow(driver, calendarName).isDisplayed(), true);
    }

    @Test(priority=2, groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void deleteCalendar() throws InterruptedException {

        String calendarName = "testowy";

        if(! mainPage.calendarsList.isDisplayed())
            mainPage.click(mainPage.calendarsListButton);


        wait.until(ExpectedConditions.visibilityOf(mainPage.calendarsList));

        WebElement hoverElement = mainPage.getCalendarRow(driver, calendarName);

        Actions builder = new Actions(driver);
        builder.moveToElement(hoverElement).perform();
        builder.moveToElement(mainPage.getCalendarRowMenuButton(driver, calendarName))
                .click()
                .perform();

        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOf(mainPage.calendarOptionMenu));

        mainPage.calendarOptionMenuEdit.click(); // zalezne od przegladarki?

        wait.until(ExpectedConditions.visibilityOf(editCalendarPage.calendarMenuOpionsList));

        editCalendarPage.deleteCalendarLink.click();

        wait.until(ExpectedConditions.visibilityOf(editCalendarPage.deleteCalendarConfirmationButton));

        editCalendarPage.click(editCalendarPage.deleteCalendarConfirmationButton);
        editCalendarPage.click(editCalendarPage.deleteCalendarButton);


        // tu jest jeszcze dialog

        //wait.until(ExpectedConditions.);
        Assert.assertEquals(driver.getCurrentUrl(), "https://calendar.google.com/calendar/render#main_7");

// problem bo mozesz miec kilka kalendarzy o tej samej nazwie - powinnas jeszcze sprawdzac komunikat o utworzeniu?

    }
}
