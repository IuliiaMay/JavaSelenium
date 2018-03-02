package ru.maistrenko.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.maistrenko.addressbook.model.ContactData;
import ru.maistrenko.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


/**
 * Created by User on 13.02.2018.
 */
public class ContactAddAndRemoveFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditiond(){
        app.goTo().groupPage();
        if(app.db().groups().size()==0){
            app.group().create(new GroupData().withName("test1"));
        }
        app.goTo().homePage();
        if(app.db().contacts().size()==0){
            app.contact().create(new ContactData().withFirstName("firstName").withFirstName("lastName").withAddress("address"));
        }
    }

    @Test
    public void testContactAddAndRemoveFromGroup(){
        app.goTo().homePage();
        ContactData contact = app.db().contacts().iterator().next();

        GroupData group = app.db().groups().iterator().next();
        app.contact().addToGroup(contact, group);
        app.goTo().homePage();
        app.contact().filterByGroup(group.getName());

       // assertThat(app.contact().all().contains(contact), equalTo(true));
        app.contact().removeContactFromGroup(contact);
        app.goTo().homePage();
        app.contact().filterByGroup(group.getName());
        assertThat(app.contact().all().contains(contact), equalTo(false));
    }
}
