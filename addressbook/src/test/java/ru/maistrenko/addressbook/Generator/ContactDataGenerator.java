package ru.maistrenko.addressbook.Generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.maistrenko.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 05.02.2018.
 */
public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contacts count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data Format")
    public String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException e){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if(format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if(format.equals("xml")){
            saveAsXml(contacts, new File(file));
        }else if(format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json =gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contacts);
        try(Writer writer = new FileWriter(file)) {
           writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try(Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstName(), contact.getLastName(), contact.getAddress(),
                        contact.getMobilePhone(), contact.getWorkPhone(), contact.getHomePhone(),
                        contact.getEmail(), contact.getEmail2(), contact.getEmail3()));
            }
        }

    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for(int i = 0; i<= count; i++){
            contacts.add(new ContactData().withFirstName("First " + i).withLastName("Last "+i).withAddress("Address " + i)
            .withMobilePhone("mobile " + i).withWorkPhone("work "+i).withHomePhone("home " + i)
            .withEmail("email@first." + i).withEmail2("email@Sec." + i).withEmail3("email@Third." + i));
        }
        return contacts;
    }
}
