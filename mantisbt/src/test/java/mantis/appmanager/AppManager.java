package mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 15.02.2018.
 */
public class AppManager {
    private WebDriver driver;
    private String browser;
    private Properties properties;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mail;
    private JamesHelper jamesHelper;
    private SoapHelper soapHelper;

    public AppManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }


    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    }
    public void stop(){
        if(driver!=null) {
            driver.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }


    public String getProperty(String key) {
       return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if(registrationHelper==null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public WebDriver getDriver() {
        if(driver==null){
            if (browser.equals(BrowserType.FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
                driver = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
                driver = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                System.setProperty("webdriver.ie.driver", "src\\main\\resources\\drivers\\IEDriverServer.exe");
                driver = new InternetExplorerDriver();
            }
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().maximize();
        }
        return driver;
    }
    public FtpHelper ftp(){
       if(ftp==null){
           ftp = new FtpHelper(this);
       }
       return ftp;
    }
    
    public MailHelper mail(){
        if(mail==null){
            mail=new MailHelper(this);
        }
        return mail;
    }

    public JamesHelper james(){
        if(jamesHelper==null){
           jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }
    public SoapHelper soap(){

        if(soapHelper==null){
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}
