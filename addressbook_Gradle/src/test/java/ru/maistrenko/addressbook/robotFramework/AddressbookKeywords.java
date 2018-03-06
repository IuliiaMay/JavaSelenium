package ru.maistrenko.addressbook.robotFramework;

import org.openqa.selenium.remote.BrowserType;
import ru.maistrenko.addressbook.appmanager.AppManager;
import ru.maistrenko.addressbook.model.ContactData;
import ru.maistrenko.addressbook.model.GroupData;

import java.io.IOException;

/**
 * Created by User on 06.03.2018.
 */
public class AddressbookKeywords {

    private  AppManager app;
    public  static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";


    public void initApplicationManager() throws IOException {
        app = new AppManager(System.getProperty("browser", BrowserType.CHROME));
        app.init();
    }
    public void stopApplicationManager(){
        app.stop();
        app = null;
    }

    public int getGroupCount(){
        app.goTo().groupPage();
        return app.group().count();
    }

    public void createGroup(String name, String header, String footer){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(name).withHeader(header).withFooter(footer));
    }

    public void removeGroup(){
        app.goTo().groupPage();
        app.group().delete(app.db().groups().iterator().next());
    }

    public void createContact(String firstName, String lastName, String address){
        app.goTo().homePage();
        app.contact().create(new ContactData().withFirstName(firstName).withLastName(lastName).withAddress(address));
    }
    public int getContactCount(){
        app.goTo().homePage();
        return  app.contact().count();
    }
    public void removeContact(){
        app.goTo().homePage();
        app.contact().delete(app.db().contacts().iterator().next());
    }
}
