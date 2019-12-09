package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;
public class ContactDeletionTests extends TestBase {
    @Test
    public void singleContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after =app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size()-1);
        before.remove(before.size()-1);
        Assert.assertEquals(before, after);

    }

}
