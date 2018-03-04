package mantis.test;

import mantis.model.MailMessage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by User on 23.02.2018.
 */
public class RegistrationByMailTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost.localdomain", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.registration().start(user, email);
        List<MailMessage> mailMessage =  app.mail().waitForMail(2, 10000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessage, email);
        app.registration().finish(confirmationLink, password, user);
        Assert.assertTrue(app.newSession().login(user, password));


    }


}
