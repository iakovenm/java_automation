package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void singleContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage();

    }

}
