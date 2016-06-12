
import pages.EditCalendarPage;
import pages.LoginPage;
import pages.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.NewCalendarPage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Mohru on 2016-06-07.
 */
public class BaseTest {

    static WebDriver driver;
    protected static WebDriverWait wait;
    public static LoginPage loginPage;
    public static MainPage mainPage;
    public static EditCalendarPage editCalendarPage;
    public static NewCalendarPage newCalendarPage;

    @Parameters("browserType")
    @BeforeClass(alwaysRun=true, groups="chrome")
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
                driver = new InternetExplorerDriver();
                break;
            case "opera":
                // jakies dodatkowe ustawienia pod opere
                driver = new ChromeDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 5);

        loginPage = PageFactory.initElements(driver, LoginPage.class);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        editCalendarPage = PageFactory.initElements(driver, EditCalendarPage.class);
        newCalendarPage = PageFactory.initElements(driver, NewCalendarPage.class);
    }
/*
    @BeforeGroups(groups = { "firefox"})
    public void fireFoxSetUp() {

        //System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\chromedriver.exe");
        driver = new FirefoxDriver();
    }
*/


    @AfterGroups(groups = { "chrome", "firefox", "safari", "opera"})
    public static void tearDown2() {
        driver.quit();
    }
}
