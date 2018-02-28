package ru.maistrenko.addressbook.test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.maistrenko.addressbook.model.ContactData;
import ru.maistrenko.addressbook.model.Contacts;
import ru.maistrenko.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


/**
 * Created by User on 28.01.2018.
 */
public  class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if(app.db().contacts().size()==0){
            app.goTo().groupPage();
            if(app.db().contacts().size()==0){
                app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test1"));
            }
            app.goTo().homePage();
            app.contact()
                    .create(new ContactData()
                            .withFirstName("Maistrenko").withLastName("Iuliia").withAddress("Samara, Lenina, 14, 108")
                            .withMobilePhone("380673657929").withHomePhone("0000")
                            .withWorkPhone("522")
                            .withEmail("jyl64uk@mail.ru"));
        }
    }

    @Test
        public void testContactModification(){
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirstName("modified").withLastName("test3").withAddress("test3").withMobilePhone("424112").withEmail("email@mail.ru");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        verifyContactListInUI();
        assertThat(before.withAdded(contact).without(modifiedContact), equalTo(after));

    }

}


