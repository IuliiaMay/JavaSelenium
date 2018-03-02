package ru.maistrenko.addressbook.sandbox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by maistrenko on 16.05.2017.
 */
public class SelenuimMainPage {


    //  WebDriver driver1 = new ChromeDriver();
    WebDriver driver = new ChromeDriver();
}
 /*   @Test
    public void navigateToGoogle() throws InterruptedException {
        try {
            driver1.manage().window().maximize();
            driver1.navigate().to("http://json.parser.online.fr/");
            WebElement o = driver1.findElement(By.xpath("//*[@id=\"header\"]/b"));
            Assert.assertNotNull("Element not found", o);
            Thread.sleep(3000);
            String title = driver1.getTitle();
            System.out.println(title);
            WebElement selector = driver1.findElement(By.xpath("//span[contains(text(), \"Options\")]"));
            selector.click();
            Thread.sleep(3000);
            driver1.findElement(By.xpath("//*[@id=\"zN\"]")).click();
            Thread.sleep(3000);
        }
        finally {
            driver1.quit();
        }
        System.out.println("Finished");

    }
    */

