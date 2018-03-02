package ru.maistrenko.addressbook.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import ru.maistrenko.addressbook.appmanager.AppManager;
import org.openqa.selenium.remote.BrowserType;
import ru.maistrenko.addressbook.model.ContactData;
import ru.maistrenko.addressbook.model.Contacts;
import ru.maistrenko.addressbook.model.GroupData;
import ru.maistrenko.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by maistrenko on 25.07.2017.
 */
public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class);

   protected static final AppManager app = new AppManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() throws Exception {
          app.driver.quit();
    }
    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));

    }
    @AfterMethod (alwaysRun = true)
    public void LogTestStop(Method m){
        logger.info("Stop test " + m.getName());

    }
    public void verifyGroupListInUI(){
        if(Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                    .collect(Collectors.toSet())));
        }
    }
    public void verifyContactListInUI(){
        if(Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((c) -> new ContactData().withId(c.getId()).withFirstName(c.getFirstName()).withLastName(c.getLastName())
                    .withAllPhones(String.format("%s\n%s\n%s", c.getHomePhone(), c.getMobilePhone(), c.getWorkPhone()))
                    .withAddress(c.getAddress())
                    .withAllEmails(String.format("%s\n%s\n%s", c.getEmail(), c.getEmail2(), c.getEmail3())))
                    .collect(Collectors.toSet())));
        }
    }

}
