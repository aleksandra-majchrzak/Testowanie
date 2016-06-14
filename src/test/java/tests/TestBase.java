package tests;

import org.openqa.selenium.safari.SafariDriver;
import pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mohru on 2016-06-07.
 */
public class TestBase {

    static WebDriver driver;
    public static WebDriverWait wait;
    public static LoginPage loginPage;
    public static MainPage mainPage;
    public static EditCalendarPage editCalendarPage;
    public static NewCalendarPage newCalendarPage;
    public static CalendarsSettingsPage calendarsSettingsPage;
    public static EventPage eventPage;


    @Parameters("browserType")
    @BeforeClass(alwaysRun=true, groups={"chrome", "firefox", "ie", "opera", "safari"})
    public void setUp(@Optional("chrome") String browserType) throws Exception{

        switch(browserType){
            case "chrome":
                System.setProperty("webdriver.chrome.driver","E:\\a_studia\\testowanie\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver","C:\\Program Files\\Internet Explorer\\selenium-driver\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            case "opera":
                // potrzebne dodatkowe ustawienie pod Opere
                driver = new ChromeDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 5);

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        editCalendarPage = PageFactory.initElements(driver, EditCalendarPage.class);
        newCalendarPage = PageFactory.initElements(driver, NewCalendarPage.class);
        calendarsSettingsPage = PageFactory.initElements(driver, CalendarsSettingsPage.class);
        eventPage = PageFactory.initElements(driver, EventPage.class);
        eventPage.setDriver(driver);
    }


    @AfterGroups(groups = { "chrome", "firefox", "safari", "opera", "ie"})
    public static void tearDown2() {
        driver.quit();
    }
}
