package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Mohru on 11.06.2016.
 */
public class MainPage {

    @FindBy(id="lhscalinner_my") public WebElement calendarsList;
    @FindBy (id="clst_my") public WebElement calendarsListButton;
    @FindBy (id="clst_my_menu") public WebElement calendarsOptionButton;
    @FindBy (id="addP") public WebElement calendarsOptionMenu;
    @FindBy (xpath="//div[contains(@class, 'goog-menuitem-content')][1]") public WebElement calendarsOptionMenuAddNew;
    @FindBy (id="cal-list-menu-popup") public WebElement calendarOptionMenu;
    @FindBy (id=":w.se") public WebElement calendarOptionMenuEdit;

    public void click(WebElement element){
        element.click();
    }

    public WebElement getCalendarRow(WebDriver driver, String calendarName){
        return driver.findElement(By.xpath("//div[contains(@class, 'calListLabel')]/span[text() = '" + calendarName+"']"));
    }

    public WebElement getCalendarRowMenuButton(WebDriver driver, String calendarName){
        return driver.findElement(By.xpath("//div[contains(@class, 'calListLabel')]/span[text() = '" + calendarName+"']/parent::div/parent::div/parent::div/following-sibling::div"));
    }
}
