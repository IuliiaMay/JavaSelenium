package ru.maistrenko.addressbook.test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.maistrenko.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by User on 03.02.2018.
 */
public class ContactDetailsTests extends TestBase {
    @BeforeTest
    public void ensurePrecondition() {
        app.goTo().homePage();
        if(app.group().all().size()==0) {
            ContactData contact = new ContactData().withFirstName("First").withLastName("Last")
                    .withAddress("Address").withHomePhone("home").withMobilePhone("mobile").withWorkPhone("work")
                    .withEmail("email").withEmail2("email2").withEmail3("email3");
            app.contact().create(contact);
        }
    }

    @Test //(enabled = false)
    public void testDetailsContactDetails(){
        ContactData contact =app.contact().all().iterator().next();
        ContactData contactInfoFromDetailsForm = app.contact().contactInfoFromDetailsForm(contact);
        assertThat(contact.getFullName(), equalTo(mergeDetails(contactInfoFromDetailsForm)));

    }


    //HINT https://gist.github.com/IvanQL/738fce67874adea6d8567d77edd19f67
    private String mergeDetails(ContactData contactInfoFromDetailsForm) {
        return null;
    }
}
