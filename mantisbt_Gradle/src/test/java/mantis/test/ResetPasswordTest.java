package mantis.test;

import mantis.model.MailMessage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;


/**
 * Created by User on 21.02.2018.
 */
public class ResetPasswordTest extends TestBase {
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

    @Test
    public void testResetPassword() throws IOException {
        app.registration().loginByUI("administrator", "root");
        app.registration().selectUser();
        String user = app.getDriver().findElement(By.cssSelector("input[name='username']")).getAttribute("value");
        String email = app.getDriver().findElement(By.cssSelector("input[name='email']")).getAttribute("value");
        String password = "password";
        app.registration().resetPassword();
        List<MailMessage> mailMessage =  app.mail().waitForMail(1, 10000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessage, email);
        app.registration().finish(confirmationLink, password, user);
        Assert.assertTrue(app.newSession().login(user, password));




    }
}
