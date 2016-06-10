
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mohru on 2016-06-07.
 */
public class CalendarTest extends BaseTest{


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        super.chromeSetUp();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @BeforeTest
    public void setUpMainPage() throws Exception {
        driver.get("https://www.google.com/calendar");

    }

  //  @Test(groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void openNewCalendarSite() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("clst_my_menu"))));
        driver.findElement(By.id("clst_my_menu")).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("addP"))));
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".goog-menuitem-content:first-child")).click();

        Thread.sleep(1000);
    }

    @Test(groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void CreateCalendarWithoutName() throws InterruptedException {
        openNewCalendarSite();

        driver.findElement(By.id("settings_save_btn")).click();
        Thread.sleep(1000);
        assertEquals(wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".cal-dialog")))).isDisplayed(), true);   //tu ewentualnie mozesz porownywac tekst

    }

    @Test(groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void CreateCalendarWithName() throws InterruptedException {
        openNewCalendarSite();

        String calendarName = "testowy";

        driver.findElement(By.id("cnInput")).sendKeys(calendarName);

        driver.findElement(By.id("settings_save_btn")).click();

        Thread.sleep(1000);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("nt2"))));   // pytanie czy id nie jest generowane jakos dynamicznie
   //     assertEquals(wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".calList .calRow .calListLabel span")))).getText(), calendarName);
// problem bo mozesz miec kilka kalendarzy o tej samej nazwie - powinnas jeszcze sprawdzac komunikat o utworzeniu?
        //driver.findElement(By.xpath("//div[contains(@class, 'calListLabel') and text() = '" + calendarName+"']"));
        driver.findElement(By.xpath("//div[contains(@class, 'calListLabel')]/span[text() = '" + calendarName+"']"));
//        assertEquals(true, true);
    }
}
