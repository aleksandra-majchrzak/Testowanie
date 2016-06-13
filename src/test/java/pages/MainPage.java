package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBase;

import java.io.File;

import static tests.TestBase.wait;

/**
 * Created by Mohru on 11.06.2016.
 */
public class MainPage {

    @FindBy(id="lhscalinner_my") public WebElement calendarsList;
    @FindBy (id="clst_my") public WebElement calendarsListButton;
    @FindBy (id="clst_my_menu") public WebElement calendarsOptionButton;
    @FindBy (id="addP") public WebElement calendarsOptionMenu;
    //@FindBy (xpath="//div[contains(@class, 'goog-menuitem-content')][1]") public WebElement calendarsOptionMenuAddNew;
    @FindBy (xpath="//div[contains(@id, 'addP')]/div[contains(@class, 'goog-menuitem')][1]") public WebElement calendarsOptionMenuAddNew;
    @FindBy (id="cal-list-menu-popup") public WebElement calendarOptionMenu;
    @FindBy (id=":w.se") public WebElement calendarOptionMenuEdit;

    @FindBy (xpath="//div[contains(@id, 'addP')]/div[contains(@class, 'goog-menuitem')][2]") public WebElement  calendarsOptionMenuSettings;

    @FindBy(xpath=".//*[@id='sidebar']/div/div/div/div/div/div[2]") public WebElement CreateButton;


    public void click(WebElement element){
        element.click();
    }

    public WebElement getCalendarRow(WebDriver driver, String calendarName){
        return driver.findElement(By.xpath("//div[contains(@class, 'calListLabel')]/span[text() = '" + calendarName+"']"));
    }

    public WebElement getCalendarRowMenuButton(WebDriver driver, String calendarName){
        return driver.findElement(By.xpath("//div[contains(@class, 'calListLabel')]/span[text() = '" + calendarName+"']/parent::div/parent::div/parent::div/following-sibling::div"));
    }

    public void openCalendarSettings(WebDriver driver, String calendarName){

        WebElement hoverElement = getCalendarRow(driver, calendarName);

        Actions builder = new Actions(driver);
        builder.moveToElement(hoverElement).perform();
        builder.moveToElement(getCalendarRowMenuButton(driver, calendarName))
                .click()
                .perform();

        TestBase.wait.until(ExpectedConditions.visibilityOf(calendarOptionMenu));

        calendarOptionMenuEdit.click(); // zalezne od przegladarki?
    }

    public void openCalendarsSettings() throws Exception{

        calendarsOptionButton.click();
        TestBase.wait.until(ExpectedConditions.visibilityOf(calendarsOptionMenu));
        calendarsOptionMenuSettings.click();
        //TestBase.wait.until(ExpectedConditions.visibilityOf());

    }


    public void createEvent(){

        TestBase.wait.until(ExpectedConditions.visibilityOf(CreateButton));
        CreateButton.click();
    }

}
