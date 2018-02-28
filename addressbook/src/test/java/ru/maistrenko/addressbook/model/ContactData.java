package ru.maistrenko.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 24.01.2018.
 */
@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {

   @ManyToMany (fetch = FetchType.EAGER)
   @JoinTable(name = "address_in_groups",
           joinColumns = @JoinColumn( name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
   private Set<GroupData> groups = new HashSet<>();

    @Column(name = "firstname")
    @Expose
    private String firstName;

    @Column(name = "lastname")
    @Expose
    private String lastName;

    @Transient
    @Expose
    private String fullName;

    @Expose
    @Column(name = "address")
    @Type(type ="text")
    private String address;

    @Expose
    @Type(type ="text")
    @Column(name = "email")
    private String email;

    @Expose
    @Type(type ="text")
    @Column(name = "email2")
    private String email2;

    @Expose
    @Type(type ="text")
    @Column(name = "email3")
    private String email3;

    @XStreamOmitField
    @Transient
    private String allEmails;

    @Column(name ="mobile")
    @Expose
    @Type(type ="text")
    private String mobilePhone;

    @Column(name ="home")
    @Expose
    @Type(type ="text")
    private String homePhone;

    @Column(name ="work")
    @Expose
    @Type(type ="text")
    private String workPhone;

    @XStreamOmitField
    @Transient
    private String allPhones;

    @Id
    @Column(name = "id")

    @XStreamOmitField
    private int id;

    @XStreamOmitField
    @Type(type ="text")
    @Column(name = "photo")
    @Transient
    private String photo;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }



    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }


    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getEmail2() {
        return email2;
    }

    public String getFullName() {
        return fullName;
    }

    public ContactData withFullName(String fullName) {
        this.fullName = fullName;
        return  this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public File getPhoto() {
        if(photo!=null) {
            return new File(photo);
        } else return null;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobilePhone(String phone) {
        this.mobilePhone = phone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        return workPhone != null ? workPhone.equals(that.workPhone) : that.workPhone == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
               "groups=" + groups +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id=" + id +
                '}';
    }


}
