package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com","test1"), true);
        }
        List<ContactData> before =app.getContactHelper().getContactList();
         ContactData contact = new ContactData(before.get(before.size()-1).getId(),"Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com",null);
        app.getContactHelper().initEditFirstContact();
        app.getContactHelper().fillContactForm((contact),false);
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after=app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());
        before.remove(before.size()-1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before),new HashSet<>(after));
    }
}
