package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemoveFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        if (contacts.size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstname("Olga").withLastname("sharko").
                    withMobilephone("+48666777545").withEmail("iakovenko.mariia3@gmail.com").
                    withAddress("podlanska").withEmail2("email2").withEmail3("email3").
                    withHomephone("homephone").withWorkphone("workphone"), true);
        }
        if (groups.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("header").withFooter("footer"));
        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        Groups groups = app.db().groups();
        GroupData removedGroup = groups.iterator().next();
        String removedGroupName = removedGroup.getName();
        Contacts before = app.db().contacts();
        ContactData addedToGroupContact = before.iterator().next();
        ContactData contact = new ContactData().withId(addedToGroupContact.getId()).withFirstname(addedToGroupContact.getFirstname()).
                withLastname(addedToGroupContact.getLastname()).withMobilephone(addedToGroupContact.getMobilephone()).
                withWorkphone(addedToGroupContact.getWorkphone()).withHomephone(addedToGroupContact.getHomephone()).
                withAddress(addedToGroupContact.getAddress()).withEmail(addedToGroupContact.getEmail()).
                withEmail2(addedToGroupContact.getEmail2()).withEmail3(addedToGroupContact.getEmail3()).inGroup(removedGroup);
        Groups beforeGroups = contact.getGroups();
        app.goTo().homePage();
        app.contact().removeContactFromGroup(contact, removedGroupName);
        Groups afterGroups = contact.getGroups();
        assertThat(afterGroups, equalTo(beforeGroups));
    }
}
