package tests;

import dataProviders.ExportImportDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mohru on 2016-06-07.
 */
public class ImportExportTest extends TestBase {

    @Parameters("browserType")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserType) throws Exception {

    }

    @BeforeMethod(alwaysRun = true)
    public void setUpMainPage() throws Exception {
        driver.get("https://www.google.com/calendar");

    }

    @Test(priority=3, groups={"chrome", "firefox", "ie", "opera", "safari"}, dataProviderClass= ExportImportDataProvider.class, dataProvider= "daneExport")
    public void ExportCalendarTest(String calendarName, String exportPath, String fileName) throws Exception{

        if (!mainPage.calendarsList.isDisplayed())
            mainPage.calendarsListButton.click();

        wait.until(ExpectedConditions.visibilityOf(mainPage.calendarsList));
        mainPage.openCalendarSettings(driver, calendarName);
        wait.until(ExpectedConditions.visibilityOf(editCalendarPage.calendarMenuOpionsList));
        driver.findElement(By.cssSelector("[title='Eksportuj ten kalendarz'")).click();
        Thread.sleep(3000);
        Assert.assertTrue(editCalendarPage.isFileExported(exportPath, fileName), "Fail");

    }

    @Test(priority=3, groups={"chrome", "firefox", "ie", "opera", "safari"}, dataProviderClass= ExportImportDataProvider.class, dataProvider= "daneImport")
    public void ImportCalendarTest(String fileName, String calendarName, String info) throws Exception{

        mainPage.openCalendarsSettings();
        Thread.sleep(1000);
        calendarsSettingsPage.startImport();
        calendarsSettingsPage.insertFileName(fileName);
        calendarsSettingsPage.finishImport(calendarName, info);

    }

}
