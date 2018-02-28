package ru.maistrenko.addressbook.test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.maistrenko.addressbook.model.ContactData;
import ru.maistrenko.addressbook.model.Contacts;
import ru.maistrenko.addressbook.model.GroupData;
import ru.maistrenko.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


/**
 * Created by User on 24.01.2018.
 */
public class ContactCreationTests extends TestBase{


    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            reader.close();
            Gson gson = new Gson();

            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }
    @DataProvider
    public Iterator<Object[]> validContactWithPhoto(){
        List<Object[]> list = new ArrayList<Object[]>();

        list.add(new Object[] {new ContactData()
                .withFirstName("Maistrenko").withLastName("Iuliia").withAddress("Samara, Lenina, 14, 108")
                .withMobilePhone("380673657929").withHomePhone("655")
                .withEmail("jyl64uk@mail.ru")
          //      .withPhoto(new File("src/test/resources/pict.png"))
     //   .inGroup(groups.iterator().next())
                });

        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> invalidContactWithPhoto(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData()
                .withFirstName("Maistrenko'").withLastName("Iuliia").withAddress("Samara, Lenina, 14, 108")
                .withMobilePhone("380673657929").withHomePhone("655")
                .withEmail("jyl64uk@mail.ru")
                .withPhoto(new File("src/test/resources/pict.png"))
                .getGroups().iterator().next()});

        return list.iterator();
    }

    @BeforeTest
    public void ensurePrecondition(){
        app.goTo().groupPage();
        Groups groups = app.db().groups();
        if(groups.size()==0){
            app.group().create(new GroupData().withName("test1").withHeader("test1").withFooter("test1"));
        }
        app.goTo().homePage();
    }


    @Test (dataProvider = "validContactsFromJson" )
    public void testContactCreation(ContactData contact){
        Contacts before = app.db().contacts();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before
                .withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));
        verifyContactListInUI();

    }

    @Test (enabled = false)
            //(dataProvider = "validContactWithPhoto")
    public void testContactCreationWithPhoto(ContactData contact){
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        File photo = new File("src/test/resources/pict.png");
        app.contact().create(contact.withPhoto(photo).inGroup(groups.iterator().next()));
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.db().contacts();
        verifyContactListInUI();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));


    }

    @Test (dataProvider = "invalidContactWithPhoto")
    public void testBadContactCreation(ContactData contact){
        Contacts before = app.contact().all();
        app.contact().createPhoto(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
        verifyContactListInUI();

    }

}
