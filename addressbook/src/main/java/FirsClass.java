import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by maistrenko on 15.05.2017.
 */
public class FirsClass {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("http://vk.com");
        Thread.sleep(3000);
        driver.quit();
    }

    public void helloTest() {
        System.out.println("Hello world!");

    }
}

