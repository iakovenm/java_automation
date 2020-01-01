package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTest extends TestBase {
    @Test
    public void testContactRemoveFromGroup(){
        Groups groups = app.db().groups();
        GroupData removedGroup = groups.iterator().next();
       Contacts before = app.db().contacts();
        ContactData addedToGroupContact = before.iterator().next();
        //Groups beforeGroups = addedToGroupContact.getGroups();
        ContactData contact = new ContactData().withId(addedToGroupContact.getId()).withFirstname(addedToGroupContact.getFirstname()).
                withLastname(addedToGroupContact.getLastname()).withMobilephone(addedToGroupContact.getMobilephone()).
                withWorkphone(addedToGroupContact.getWorkphone()).withHomephone(addedToGroupContact.getHomephone()).
                withAddress(addedToGroupContact.getAddress()).withEmail(addedToGroupContact.getEmail()).
                withEmail2(addedToGroupContact.getEmail2()).withEmail3(addedToGroupContact.getEmail3()).inGroup(removedGroup);
       Groups beforeGroups = contact.getGroups();
        app.goTo().homePage();
        app.contact().removeContactFromGroup(contact);
        Groups afterGroups = contact.getGroups();
        assertThat(afterGroups,equalTo(beforeGroups));
    }
}
