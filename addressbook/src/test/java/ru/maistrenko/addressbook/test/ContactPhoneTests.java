package ru.maistrenko.addressbook.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import ru.maistrenko.addressbook.model.ContactData;
import ru.maistrenko.addressbook.model.GroupData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by User on 02.02.2018.
 */
public class ContactPhoneTests extends TestBase {
   @BeforeMethod
    public void ensurePrecondition(){
        app.goTo().homePage();
        if(app.contact().all().size()==0){
            app.goTo().groupPage();
            if(app.group().all().size()==0){
                app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test1"));
            }
            app.goTo().homePage();
            app.contact()
                    .create(new ContactData()
                            .withFirstName("Maistrenko").withLastName("Iuliia").withAddress("Samara, Lenina, 14, 108")
                            .withHomePhone("-111").withWorkPhone("(22)2").withMobilePhone("3 8067 365 79 29")
                            .withEmail("jyl64uk@mail.ru").withEmail2("email2@test.test").withEmail3("64"));
        }
    }

    @Test
    public void testContactPhone(){
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().contactInfoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }

    private String  mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(),contact.getEmail3()).stream()
                .filter((s)-> !s.equals(""))
                .map(ContactPhoneTests::cleanedEmail)
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).stream()
                .filter((s)-> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
    public static String cleanedEmail(String email){
        return email.replaceAll(" ", "");
    }
}
