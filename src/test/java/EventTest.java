import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

import static com.thoughtworks.selenium.SeleneseTestBase.fail;
import static org.testng.Assert.assertEquals;

/**
 * Created by Mohru on 2016-06-07.
 */
public class EventTest  extends BaseTest{

    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    public WebDriverWait wait;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
    //    super.fireFoxSetUp();
        super.chromeSetUp();
        baseUrl = "https://calendar.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

//    @Test(groups = { "firefox", "chrome"})
//    public void test1() throws Exception {
//        driver.get(baseUrl + "/calendar/render#main_7");
//        driver.findElement(By.xpath("//div[@id='bubbleContent:5k']/div/div/div[2]/div/div/div/div/div[4]/div/div/div/div/div[2]")).click();
//        driver.findElement(By.xpath("//div[@id='sidebar']/div/div/div/div/div/div[2]")).click();
//    }

//    @AfterClass(alwaysRun = true)
//    public void tearDown() throws Exception {
//        driver.quit();
//        String verificationErrorString = verificationErrors.toString();
//        if (!"".equals(verificationErrorString)) {
//            fail(verificationErrorString);
//        }
//    }

    @Test(groups="chrome")

    public void WebPage() throws Exception{


    }

    @Test(groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void emptyTest2(){
        assertEquals(true, true);
    }

    @Test(groups = { "chrome"})//, "firefox", "opera", "safari" })
    public void emptyTest3(){
        assertEquals(true, true);
    }
}
