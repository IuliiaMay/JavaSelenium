package mantis.test;


import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import mantis.appmanager.AppManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Arrays;

/**
 * Created by User on 15.02.2018.
 */
public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final AppManager app = new AppManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(
                new File("src/test/resources/config_inc.php"),
                "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));

    }
    @AfterMethod(alwaysRun = true)
    public void LogTestStop(Method m){
        logger.info("Stop test " + m.getName());

    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnect();
        ObjectRef statusIssue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId)).getStatus();
        if(statusIssue.equals("resolved") || statusIssue.equals("closed")){
            return false;
        }
        return true;
    }

}
