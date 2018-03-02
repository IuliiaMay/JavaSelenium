package ru.maistrenko.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 20.01.2018.
 */
public class SessionHelper extends HelperBase {
    public SessionHelper(WebDriver driver) {
        super(driver);
    }
    public void login(String username, String password) {
        type(By.name("user"), username);
        type(By.name("pass"), password);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }
}
