package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Created by Mohru on 11.06.2016.
 */
public class LoginPage{

    WebDriver driver;

    @FindBy (id="Email") public WebElement EmailField;
    @FindBy (id="next") WebElement NextButton;
    @FindBy (id="Passwd") public WebElement PasswdField;
    @FindBy(id="signIn")
    WebElement SignInButton;
    @FindBy (id="mainlogo") public WebElement Logo;
    @FindBy (id="errormsg_0_Email") public WebElement Error1;
    @FindBy (id="errormsg_0_Passwd") public WebElement Error2;

    public void setText(WebElement element, String text){

        element.clear();
        element.sendKeys(text);
        Assert.assertEquals(element.getAttribute("value"), text);
    }

    public void click(WebElement element){
        element.click();
    }

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void setEmail(String text){
        setText(EmailField, text);
    }

    public void setPassword(String text){
        setText(PasswdField, text);
    }

    public void clickNext(){
        click(NextButton);
    }

    public void clickSignIn(){
        click(SignInButton);
    }

    public boolean chceckError1(String text){

        try{
            return Error1.getText().equals(text);
        }catch(NoSuchElementException e){
            return false;
        }

    }

    public boolean chceckError2(String text){

        try{
            return Error2.getText().equals(text);
        }catch(NoSuchElementException e){
            return false;
        }

    }
}

