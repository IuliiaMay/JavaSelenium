package ru.maistrenko.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by User on 31.01.2018.
 */
public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts (Contacts contacts){
        this.delegate = new HashSet<ContactData>(contacts.delegate);
    }

    public Contacts (){
        this.delegate = new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) {
        this.delegate = new HashSet<ContactData>(contacts);
    }

    public Contacts withAdded(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;
    }

    public Contacts without(ContactData contact){
        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;
    }

}