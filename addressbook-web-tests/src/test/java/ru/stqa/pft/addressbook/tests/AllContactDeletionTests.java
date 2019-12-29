package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AllContactDeletionTests extends TestBase {
    @Test (enabled = false)
    public void allContactDeletion() {
        app.goTo().homePage();
        if (!app.contact().isThereAContact()) {
            app.contact().create(new ContactData().withFirstname("Maria").withLastname("Iakovenko").withMobilephone("+48666777545").withEmail("iakovenko.mariia3@gmail.com"), true);
        }
        app.contact().selectAllContacts();
        app.contact().deleteContact();
        app.goTo().homePage();
    }
}
