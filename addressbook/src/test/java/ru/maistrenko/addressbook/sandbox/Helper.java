package ru.maistrenko.addressbook.sandbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by maistrenko on 18.05.2017.
 */
public class Helper {
    private WebDriver driver;
    public Helper(WebDriver driver){
        this.driver=driver;
    }
    public void searchAndClick(String string){
         driver.findElement(By.xpath(string)).click();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

}
