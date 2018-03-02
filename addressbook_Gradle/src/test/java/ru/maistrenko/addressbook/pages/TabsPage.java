package ru.maistrenko.addressbook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by maistrenko on 28.07.2017.
 */
public class TabsPage extends AbstractPage {
    public TabsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String firstTabId = "ui-id-1";
    public String firstTabTextWindow = "tabs-1";


    public void findAndClickTab(String elementId, String elementForValidation, String textForValidation){
        WebElement tab = driver.findElement(By.id(elementId));
        tab.click();
        Assert.assertEquals(textForValidation, driver.findElement(By.id(elementForValidation)).getText());

    }
}
