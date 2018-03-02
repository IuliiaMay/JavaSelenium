package ru.maistrenko.addressbook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by maistrenko on 25.07.2017.
 */
public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public void openMainPage(){
        if(isElementPresent(By.tagName("h1")) && driver.findElement(By.tagName("h1")).getText().equals("Home")){
            return;
        } else {
            driver.findElement(By.linkText("Home")).click();
        }
    }

    public void openRegistrationPage() {
        if (isElementPresent(By.tagName("h1")) && driver.findElement(By.tagName("h1")).getText().equals("Registration")) {
            return;
        } else {
            driver.findElement(By.id("menu-registration")).click();
            Assert.assertEquals(driver.getTitle(), "Registration | Demoqa");
        }
    }

    public void openDraggablePage() {
        if (driver.findElement(By.tagName("h1")).getText().equals("Draggable")) {
            return;
        } else {
            driver.findElement(By.id("menu-item-140")).click();
            Assert.assertEquals(driver.getTitle(), "Draggable | Demoqa");
        }
    }
    public void openTabsPage() {
        if (driver.findElement(By.tagName("h1")).getText().equals("Tabs")) {
            return;
        } else {
            driver.findElement(By.id("menu-item-98")).click();
            Assert.assertEquals(driver.getTitle(), "Tabs | Demoqa");
        }
    }
    public void openDroppablePage() {
        if (driver.findElement(By.tagName("h1")).getText().equals("Droppable")) {
            return;
        } else {
            driver.findElement(By.id("menu-item-141")).click();
            Assert.assertEquals(driver.getTitle(), "Droppable | Demoqa");
        }
    }

}
