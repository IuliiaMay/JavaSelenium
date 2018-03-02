package ru.maistrenko.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.maistrenko.addressbook.model.ContactData;
import ru.maistrenko.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;



/**
 * Created by User on 24.01.2018.
 */
public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().homePage();
        if(app.db().contacts().size()==0){
            app.contact().create(new ContactData()
                    .withFirstName("Maistrenko").withLastName("Iuliia").withAddress("Samara, Lenina, 14, 108")
                    .withHomePhone("0000").withWorkPhone("522")
                    .withEmail("jyl64uk@mail.ru"));
        }

    }

    @Test
    public void testContactDeletion(){
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size()-1));
        Contacts after = app.contact().all();
        verifyContactListInUI();
        assertThat(after, equalTo(before.without(deletedContact)));
    }



}
