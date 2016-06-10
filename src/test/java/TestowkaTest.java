import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeoutException;

/**
 * Created by Mohru on 2016-06-07.
 */
public class TestowkaTest {

    static WebDriver driver;

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\chromedriver.exe");
        driver = new ChromeDriver(); //;FirefoxDriver();
    }

    @Test(enabled = false)
    public void ShouldBePosibleToSearchTestowkaAtGoogle()
            throws InterruptedException {
        driver.get("http://google.pl");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys("testowka.pl");
        WebElement searchButton = driver.findElement(By
                .name("btnG"));
        searchButton.click();
        for (int second = 0;; second++) {
            if (second >= 60)
                fail("timeout");
            try {
                if (driver.findElement(By.linkText("Testowka.pl"))
                        .isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
            }
            Thread.sleep(1000);
        }
        WebElement linkToTestowka = driver.findElement(By
                .linkText("Testowka.pl"));
        linkToTestowka.click();

        assertEquals(driver.getTitle(), "blog.testowka.pl");
    }

    public void fail(String message){

    }

    @AfterClass
    public static void tearDown() {
        driver.close();
    }

}
