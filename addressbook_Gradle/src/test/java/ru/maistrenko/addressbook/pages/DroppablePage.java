package ru.maistrenko.addressbook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by maistrenko on 31.07.2017.
 */
public class DroppablePage extends AbstractPage {
    public DroppablePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void dragAndDrop(String source, String target){
        WebElement elementElement = driver.findElement(By.id(source));
        WebElement targetElement = driver.findElement(By.id(target));

        new Actions(driver).dragAndDrop(elementElement, targetElement).perform();
        Assert.assertEquals("Dropped!", targetElement.getText());

    }
}
