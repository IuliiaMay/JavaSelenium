package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by maistrenko on 28.07.2017.
 */
public class HelperBase {
    protected WebDriver driver;
    protected AppManager app;

    public HelperBase(AppManager app){
        this.app = app;
        this.driver=app.getDriver();
    }
   protected void click(By locator){
        driver.findElement(locator).click();
    }

    protected void type(By locator,String text){
       click(locator);
       if(text!=null) {
           String exisingText = driver.findElement(locator).getAttribute("value");
           if(!text.equals(exisingText)) {
               driver.findElement(locator).clear();
               driver.findElement(locator).sendKeys(text);
           }
       }
    }
    protected void attach(By locator, File file){

        if(file!=null) {
              driver.findElement(locator).sendKeys(file.getAbsolutePath());
            }
        }

    public boolean isAlertPresent(){
        try{
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e){
            return false;
        }
    }

    protected boolean isElementPresent(By localor) {
        try {
            driver.findElement(localor);
            return true;
        } catch (NoSuchElementException element){
            return false;
        }
    }
}
