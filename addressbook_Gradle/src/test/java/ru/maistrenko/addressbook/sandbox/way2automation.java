package ru.maistrenko.addressbook.sandbox;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by maistrenko on 24.07.2017.
 */
public class way2automation {
    private static WebDriver driver;
    private static String baseURl;
    @BeforeClass
    public static void onceExecutedBeforeAll(){
     driver = new FirefoxDriver();
     baseURl = "http://way2automation.com/way2auto_jquery";
     driver.navigate().to(baseURl);
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testSelectable(){
        driver.navigate().to(baseURl + "/selectable.php");

    }



    }
