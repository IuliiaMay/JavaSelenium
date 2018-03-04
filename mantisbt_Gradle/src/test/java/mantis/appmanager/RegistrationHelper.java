package mantis.appmanager;

import mantis.model.MailMessage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.lanwen.verbalregex.VerbalExpression;

import java.util.List;

/**
 * Created by User on 19.02.2018.
 */
public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(AppManager app) {
        super(app);
    }

    public String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m)-> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    public void start(String username, String email) {
        driver.get(app.getProperty("web.baseUrl")+ "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[value='Зарегистрироваться']"));
    }

    public void finish(String confirmationLink, String password, String username) {
        driver.get(confirmationLink);
        type(By.name("realname"), username);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath(".//*/fieldset/span/button"));
    }
    public void resetPassword() {

      //  click(By.cssSelector("input[value='Сбросить пароль']"));
        click(By.xpath(".//*[@id='manage-user-reset-form']/fieldset/span/input"));
    }

    public void selectUser() {
        driver.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        click(By.xpath(".//*/div[2]/div[2]/div/div/div[4]/div[2]/div[2]/div/table/tbody/tr[2]/td[1]/a"));
    }

    public void loginByUI(String login, String password) {
        type(By.cssSelector("input[name='username']"), login);
        click(By.cssSelector("input[value='Войти']"));
        type(By.cssSelector("input[name='password']"), password);
        click(By.cssSelector("input[value='Войти']"));

    }
}
