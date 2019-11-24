package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        gotoContactCreationPage();
        fillContactForm(new ContactData("Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com"));
        submitContactCreation();
        returnToHomePage();
    }


}
