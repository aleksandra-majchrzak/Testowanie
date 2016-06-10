import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.Test;

/**
 * Created by Mohru on 2016-06-07.
 */
public class BaseTest {

    static WebDriver driver;
    protected WebDriverWait wait;

//    @BeforeGroups(groups = { "chrome"})
    public void chromeSetUp() {

        System.setProperty("webdriver.chrome.driver","E:\\a_studia\\testowanie\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 5);

        driver.get("https://www.google.com/calendar");
        driver.findElement(By.id("Email")).sendKeys("coproject.example");
        driver.findElement(By.id("next")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("Passwd"))));
        driver.findElement(By.id("Passwd")).sendKeys("co.example1");
        driver.findElement(By.id("signIn")).click();
    }

    @BeforeGroups(groups = { "firefox"})
    public void fireFoxSetUp() {

        //System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\chromedriver.exe");
        driver = new FirefoxDriver();
    }

    //@BeforeGroups(groups = { "opera"})
    public void operaSetUp() {

        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\chromedriver.exe");
        driver = new ChromeDriver(); //OperaDriver() - tu trzeba dodatkowe ustawienia
    }

   // @BeforeGroups(groups = { "safari"})
    public void safariSetUp() {

        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\chromedriver.exe");
        driver = new SafariDriver();
    }

    @AfterClass // Groups(groups = { "chrome"})
    public static void tearDown() {
        driver.close();
    }

    @AfterGroups(groups = { "chrome"})
    public static void tearDown2() {
        driver.quit();
    }
}
