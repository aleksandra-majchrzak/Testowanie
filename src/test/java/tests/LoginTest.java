package tests;

import dataProviders.LoginDataProvider;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Mohru on 11.06.2016.
 */
public class LoginTest extends TestBase {

    @Parameters("browserType")
    @BeforeClass(alwaysRun = true)
    public void setUp(@Optional("chrome") String browserType) throws Exception {
        //    super.fireFoxSetUp();
        super.setUp(browserType);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    @Test(priority=1, groups={"chrome", "firefox", "ie", "opera", "safari"}, dataProviderClass= LoginDataProvider.class, dataProvider="dane")

    public void LogIn_Test(String email, String pass, String errortxt1, String errortxt2) throws Exception{

        driver.get("https://www.google.com/calendar");
        driver.manage().window().maximize();
        wait.until(ExpectedConditions.visibilityOf(loginPage.EmailField));
        loginPage.setEmail(email);
        loginPage.clickNext();

        Thread.sleep(100);

        if (errortxt1 == null || errortxt1.isEmpty()){

            wait.until(ExpectedConditions.visibilityOf(loginPage.PasswdField));
            loginPage.setPassword(pass);
            loginPage.clickSignIn();

            if (errortxt2 == null || errortxt2.isEmpty()){
                wait.until(ExpectedConditions.visibilityOf(loginPage.Logo));
                Assert.assertEquals(driver.getCurrentUrl(), "https://calendar.google.com/calendar/render#main_7");

            }else{
                Assert.assertTrue(loginPage.chceckError2(errortxt2), "Excepted error  "+ errortxt2+ " but got " +loginPage.Error2.getText());

            }

        }else{
            Assert.assertTrue(loginPage.chceckError1(errortxt1), "Excepted error  "+ errortxt1+ " but got " +loginPage.Error1.getText());


        }

    }
}
