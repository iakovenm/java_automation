package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AllContactDeletionTests extends TestBase {
    @Test
    public void allContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com", "test1"), true);
        }
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage();
    }
}
