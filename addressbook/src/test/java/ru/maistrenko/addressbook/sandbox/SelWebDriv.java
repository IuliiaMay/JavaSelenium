package ru.maistrenko.addressbook.sandbox;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by maistrenko on 18.05.2017.
 */
public class SelWebDriv {


    WebDriver chromeDriver = new ChromeDriver();
     @Test
    public void navigateAndFindTitle() throws InterruptedException {
         chromeDriver.navigate().to("https://selenium2.ru/");
         chromeDriver.manage().window().maximize();
         String title = chromeDriver.getTitle();
        // System.out.println(title);
         Assert.assertTrue("Incorrect Title, should be - \"Что такое Selenium? - Selenium - автоматизация веб-приложений\"", title.equals("Что такое Selenium? - Selenium - автоматизация веб-приложений"));
         Thread.sleep(3000);
         chromeDriver.quit();
     }
     @Test
    public void clickButtons() throws InterruptedException {
        try {
            chromeDriver.navigate().to("https://selenium2.ru/");
            chromeDriver.manage().window().maximize();

            WebElement news = chromeDriver.findElement(By.xpath(".//*[@id='jsn-pos-mainmenu']/div/div/div/ul/li[3]/a/span"));
            news.click();
            Thread.sleep(3000);
            Assert.assertTrue("Current URS should be - https://selenium2.ru/news.html", chromeDriver.getCurrentUrl().equals("https://selenium2.ru/news.html"));
            WebElement articles = chromeDriver.findElement(By.xpath(".//*[@id='jsn-pos-mainmenu']/div/div/div/ul/li[4]/a/span"));
            articles.click();
            Assert.assertTrue("Current URS should be - https://selenium2.ru/articles.html", chromeDriver.getCurrentUrl().equals("https://selenium2.ru/articles.html"));

            WebElement blogs = chromeDriver.findElement(By.xpath(".//*[@id='jsn-pos-mainmenu']/div/div/div/ul/li[5]/a/span"));
            blogs.click();
            Thread.sleep(3000);
            Assert.assertTrue("Current URS should be - https://selenium2.ru/blog.html", chromeDriver.getCurrentUrl().equals("https://selenium2.ru/blog.html"));
            WebElement docs = chromeDriver.findElement(By.xpath(".//*[@id='jsn-pos-mainmenu']/div/div/div/ul/li[6]/a"));
            docs.click();
            Thread.sleep(3000);
            System.out.println(chromeDriver.getTitle());
            Assert.assertTrue("Current URS should be - https://selenium2.ru/docs.html", chromeDriver.getCurrentUrl().equals("https://selenium2.ru/docs.html"));
            WebElement trainings = chromeDriver.findElement(By.xpath(".//*[@id='jsn-pos-mainmenu']/div/div/div/ul/li[7]/a"));
            trainings.click();
            Thread.sleep(3000);
            Assert.assertTrue("Current URS should be - https://selenium2.ru/trainings.html", chromeDriver.getCurrentUrl().equals("https://selenium2.ru/trainings.html"));
            WebElement consulting = chromeDriver.findElement(By.xpath(".//*[@id='jsn-pos-mainmenu']/div/div/div/ul/li[8]/a"));
            consulting.click();
            Thread.sleep(3000);
            Assert.assertTrue("Current URS should be - https://selenium2.ru/consulting.html", chromeDriver.getCurrentUrl().equals("https://selenium2.ru/consulting.html"));
            WebElement forum = chromeDriver.findElement(By.xpath(".//*[@id='jsn-pos-mainmenu']/div/div/div/ul/li[9]/a"));
            forum.click();
            System.out.println(chromeDriver.getTitle());
            Assert.assertTrue("Incorrect Title, should be - \"Что такое Selenium? - Selenium - автоматизация веб-приложений\"", forum.equals("Что такое Selenium? - Selenium - автоматизация веб-приложений"));
        }
        finally {
            chromeDriver.quit();
        }


     }


    @Test
    public void angolaLogin()  {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("http://angola-tag.sherlock.com/app/#!");
        System.out.println("navigated");
        WebElement login = driver.findElement(By.cssSelector("//*[@id='gwt-uid-4']"));
        //login.clear();
        System.out.println("cleared");
        login.sendKeys("test1");
        System.out.println("clicked");
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector(".//*[@id='app-96801']/div/div[2]/div/div/div/div[3]/div/table/tbody/tr[5]/td[3]/div/span")).click();
        driver.quit();
    }



}
