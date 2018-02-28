package ru.maistrenko.addressbook.pages;

import org.openqa.selenium.*;
import org.testng.Assert;

/**
 * Created by maistrenko on 25.07.2017.
 */
public class AbstractPage {

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    protected void setTextById(String elementId, String elementValue) {
        if(elementValue != null) {
            String existingText = driver.findElement(By.id(elementId)).getAttribute("value");
            if(!elementValue.equals(existingText)) {
                WebElement field = driver.findElement(By.id(elementId));
                field.clear();
                field.sendKeys(elementValue);
            }
        }
    }

    protected boolean isElementPresent(By locator) {
       try {
           driver.findElement(locator);
           return true;
       } catch (NoSuchElementException exception) {
           return false;
       }
    }

    protected boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException exception) {
            return false;
        }
    }
}
