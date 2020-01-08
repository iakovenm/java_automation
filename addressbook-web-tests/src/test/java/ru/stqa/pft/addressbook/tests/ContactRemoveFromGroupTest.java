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
        } else if (!contacts.iterator().next().getGroups().contains(groups.iterator().next())) {
            for(ContactData contact:contacts){
                if(contact.getGroups().contains((groups.iterator().next()))){
                    return;
                }
            }
            app.goTo().groupPage();
            app.group().create((new GroupData().withName("test5").withHeader("header").withFooter("footer")));

        }
    }

    @Test
    public void testContactRemoveFromGroup() {
        Groups groups = app.db().groups();
        GroupData removedGroup = groups.iterator().next();
        String removedGroupName = removedGroup.getName();
        Contacts before = app.db().contacts();
        ContactData addedToGroupContact = before.stream().max((o1, o2) -> Integer.compare(o1.getGroups().size(), o2.getGroups().size())).get();
        for (ContactData c: before) {
            int i=0;
            if (c.getGroups().size()==groups.size()){
                addedToGroupContact = c;
                i++;
            } if (i>0){
                break;
            }
        }

        Groups beforeGroups = addedToGroupContact.getGroups();
        app.goTo().homePage();
        app.contact().removeContactFromGroup(addedToGroupContact, removedGroupName);
        Contacts after = app.db().contacts();
        for (ContactData d: after) {
            if(d.getId()==addedToGroupContact.getId()){
                addedToGroupContact=d;
            }
        }
        Groups afterGroups = addedToGroupContact.getGroups();
        assertThat(afterGroups, equalTo(beforeGroups));
    }
}
