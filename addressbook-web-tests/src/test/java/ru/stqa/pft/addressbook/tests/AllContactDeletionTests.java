package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AllContactDeletionTests extends TestBase {
    @Test
    public void allContactDeletion() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectAllContacts();
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().goToHomePage();
    }
}
