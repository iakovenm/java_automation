package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;


public class ContactHelper extends HelperBase {

    private Contacts contactCache = null;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobilephone());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address"), contactData.getAddress());

        //attach(By.name("photo"),contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size()>0){
                Assert.assertTrue(contactData.getGroups().size()==1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());}
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void selectContactById(int id) {
       click(By.cssSelector("td.center input[value='" + id + "']"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        // contactCache = null;
        click(By.linkText("home"));
    }

    private void addToGroup(String name) {
       new Select(wd.findElement(By.name("to_group"))).
                selectByVisibleText(name);
        click(By.name("add"));
    }

    private void removeFromGroup(ContactData contact, int id) {
        wd.findElement(By.name("group")).click();
        click(By.cssSelector("option[value='"+id+"']"));
        selectContactById(contact.getId());
       // new Select(wd.findElement(By.cssSelector("option[value='"+id+"']"))).getOptions();
        click(By.name("remove"));

    }

    public void selectAllContacts() {
        click(By.id("MassCB"));
    }

    public void initEditContactById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact, boolean creation) {
        click(By.linkText("add new"));
        fillContactForm(contact, creation);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initEditContactById(contact.getId());
        fillContactForm((contact), false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCache = null;
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {

        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastname = cells.get(1).getText();
            String name = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(name).withLastname(lastname).withAllPhones(allPhones).withAllEmails(allEmails).
                    withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initEditContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastmane = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname)
                .withLastname(lastmane).withMobilephone(mobile).withHomephone(home).
                        withWorkphone(work).withAddress(address).withEmail(email).
                        withEmail2(email2).withEmail3(email3);
    }

    public void addContactToGroup(ContactData contact, String group) {
        selectContactById(contact.getId());
        addToGroup(group);
        contactCache = null;
    }

    public void removeContactFromGroup(ContactData contact, int id) {
        removeFromGroup(contact,id);
        contactCache = null;
    }
}


