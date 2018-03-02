package ru.maistrenko.addressbook.sandbox;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



/**
 * Created by maistrenko on 18.05.2017.
 */
public class Selenium {

    private  WebDriver driver;
    @BeforeClass
    public static void onceExecutedBeforeAll() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://selenium2.ru");
        driver.manage().window().maximize();


    }
    @Before
    public void setUp() throws Exception {
    //   driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @AfterClass
    public static void tearDown() throws Exception {

    }
    @Test
    public void clickButton(){
        Helper news = new Helper(driver);
        news.searchAndClick(".//*[@id='jsn-pos-mainmenu']/div/div/div/ul/li[3]/a/span");
        Assert.assertTrue("failed", news.equals("https://selenium2.ru/articles.html"));
        Helper blogs = new Helper(driver);
        blogs.searchAndClick(".//*[@id='jsn-pos-mainmenu']/div/div/div/ul/li[5]/a/span");
        Assert.assertTrue("failed", news.equals("https://selenium2.ru/blogs.html"));

    }

}