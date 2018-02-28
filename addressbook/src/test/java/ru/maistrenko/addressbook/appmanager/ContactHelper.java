package ru.maistrenko.addressbook.appmanager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.maistrenko.addressbook.model.ContactData;
import ru.maistrenko.addressbook.model.Contacts;
import ru.maistrenko.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by User on 24.01.2018.
 */
public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void fillContactForm(ContactData contactData, boolean created) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        if (created && contactData.getGroups().size() !=0) {
            Assert.assertTrue(contactData.getGroups().size() ==1);
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        } else if (!created){
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        if(contactData.getPhoto() !=null) {
            attach(By.name("photo"), contactData.getPhoto());
        }
    }

    public void fillContactFormWithPhoto(ContactData contactData){
        fillContactForm(contactData, true);
        attach(By.name("photo"), contactData.getPhoto());
    }

    public int count() {
        return driver.findElements(By.name("selected[]")).size();
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }


    public void initContactModification() {
        click(By.cssSelector("img[alt=\"Edit\"]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }


    private void selectContactById(int id) {
        driver.findElement(By.cssSelector("input[value='" + id +"']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        driver.switchTo().alert().accept();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void createPhoto(ContactData contact) {
        initContactCreation();
        fillContactFormWithPhoto(contact);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify( ContactData contact) {
        selectContactById(contact.getId());
        initContactModification();
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
        returnToHomePage();
    }

    public void returnToHomePage() {
        click(By.linkText("home"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if(contactCache!=null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstName = element.findElement(By.xpath("./td[3]")).getText();
            String lastName = element.findElement(By.xpath("./td[2]")).getText();
            String address = element.findElement(By.xpath("./td[4]")).getText();
            String allPhones=  element.findElement(By.xpath("./td[6]")).getText();
            String allEmails=  element.findElement(By.xpath("./td[5]")).getText();
            contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public ContactData contactInfoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = driver.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = driver.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = driver.findElement(By.name("work")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");

        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withAddress(address);
    }

    private void initContactModificationById(int id) {
        WebElement checkbox = driver.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("src"));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

        /*ALTERNATIVES:
        1. driver.findElement(By.xpath(String.format("//input[@value='%s']/../..td[8]/a", id))).click();
        2. driver.findElement(By.xpath(String.format("//tr.//input[@value='%s']/td[8]/a", id))).click();
        3. driver.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
         */

    }

    public ContactData contactInfoFromDetailsForm(ContactData contact) {
        initContactDetailsById(contact.getId());

     WebElement element = By.cssSelector("div[id=container]").findElement(driver);

        String fullName = element.getText();
        return new ContactData().withId(contact.getId()).withFullName(fullName);
    }

    private void initContactDetailsById(int id) {
        driver.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroup(group.getName());
        initAddContactToGroup();
        contactCache = null;
    }

    public void removeContactFromGroup(ContactData contact) {
        selectContactById(contact.getId());
        click(By.name("remove"));
        contactCache = null;
    }

    private void selectGroup(String groupName) {
        new Select(driver.findElement(By.name("to_group"))).selectByVisibleText(groupName);
    }

    private void initAddContactToGroup() {
        click(By.name("add"));
    }


    public void filterByGroup(String groupName) {
        new Select(driver.findElement(By.name("group"))).selectByVisibleText(groupName);
    }


}
