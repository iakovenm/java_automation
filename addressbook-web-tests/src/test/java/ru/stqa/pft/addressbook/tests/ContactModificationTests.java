package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getNavigationHelper().goToHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com","test1"), true);
        }
        app.getContactHelper().initEditFirstContact();
        app.getContactHelper().fillContactForm(new ContactData("Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com",null),false
        );
        app.getContactHelper().submitContactModification();
        app.getNavigationHelper().goToHomePage();
    }
}
