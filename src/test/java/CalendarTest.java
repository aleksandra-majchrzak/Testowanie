
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

        wait.until(ExpectedConditions.visibilityOf(mp.calendarsListButton));
        mp.click(mp.calendarsOptionButton);
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(mp.calendarsOptionMenu));
        Thread.sleep(1000);
        mp.click(mp.calendarsOptionMenuAddNew);

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

        driver.findElement(By.id("cnInput")).sendKeys(calendarName);

        driver.findElement(By.id("settings_save_btn")).click();

        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nt2"))));   // pytanie czy id nie jest generowane jakos dynamicznie

        assertEquals(mp.getCalendarRow(driver, calendarName).isDisplayed(), true);
    }

    @Test(priority=2, groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void deleteCalendar() throws InterruptedException {

        String calendarName = "testowy";

        if(! mp.calendarsList.isDisplayed())
            mp.click(mp.calendarsListButton);


        wait.until(ExpectedConditions.visibilityOf(mp.calendarsList));

        WebElement hoverElement = mp.getCalendarRow(driver, calendarName);

        Actions builder = new Actions(driver);
        builder.moveToElement(hoverElement).perform();
        builder.moveToElement(mp.getCalendarRowMenuButton(driver, calendarName))
                .click()
                .perform();

        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOf(mp.calendarOptionMenu));

        mp.calendarOptionMenuEdit.click(); // zalezne od przegladarki?

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("svalues"))));

        driver.findElement(By.xpath("//*[@id='svalues']/tbody/tr[11]/td/div[1]/a")).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("se-dd-cb"))));

        driver.findElement(By.id("se-dd-cb")).click();
        driver.findElement(By.name("di_delete")).click();


        // tu jest jeszcze dialog

        //wait.until(ExpectedConditions.);
        Assert.assertEquals(driver.getCurrentUrl(), "https://calendar.google.com/calendar/render#main_7");

// problem bo mozesz miec kilka kalendarzy o tej samej nazwie - powinnas jeszcze sprawdzac komunikat o utworzeniu?

    }
}
