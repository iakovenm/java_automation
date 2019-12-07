package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().gotoContactCreationPage();
        app.getContactHelper().createContact(new ContactData("Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com", "test1"), true);
        app.getNavigationHelper().goToHomePage();

    }


}
