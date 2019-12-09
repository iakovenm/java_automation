package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.getNavigationHelper().goToHomePage();
        List<ContactData> before =app.getContactHelper().getContactList();
        app.getNavigationHelper().gotoContactCreationPage();
        ContactData contact = new ContactData("Maria", "Iakovenko", "+48666777545", "iakovenko.mariia3@gmail.com", "test1");
        app.getContactHelper().createContact((contact), true);
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after =app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() +1);
        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);




    }


}
