package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.gotoContactCreationPage();
        app.fillContactForm(new ContactData("Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com"));
        app.submitContactCreation();
        app.returnToHomePage();
    }


}
