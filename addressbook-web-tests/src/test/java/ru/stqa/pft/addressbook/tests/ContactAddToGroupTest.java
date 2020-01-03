package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddToGroupTest extends TestBase {

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
            return;
        } else if (groups.size() == contacts.iterator().next().getGroups().size()) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test5").withHeader("header").withFooter("footer"));
        } else if (contacts.iterator().next().getGroups().contains(groups.iterator().next())) {
            for(ContactData contact:contacts){
                if(!contact.getGroups().contains((groups.iterator().next()))){
                    return;
                }
            }
            app.goTo().groupPage();
            app.group().create((new GroupData().withName("test5").withHeader("header").withFooter("footer")));

        }

    }

    @Test
    public void testContactAddToGroup() {
        Groups groups = app.db().groups();
        GroupData addedGroup = new GroupData().withId(groups.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()).withName(groups.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getName());
        String addedGroupName = addedGroup.getName();
        Contacts before = app.db().contacts();
       // ContactData addedToGroupContact = before.stream().map((g) -> new ContactData().
         //       withId(g.getId()).withFirstname(g.getFirstname()).withLastname(g.getLastname()).inGroup(g.getGroups().removeIf()).collect(Collectors.toSet())));
        ContactData addedToGroupContact = before.iterator().next();
        for(ContactData c: before){
        if (!addedToGroupContact.getGroups().contains(addedGroup)){
        }else{         before.iterator().next();
        }
       }
        ContactData contact = new ContactData().withId(addedToGroupContact.getId()).withFirstname(addedToGroupContact.getFirstname()).
                withLastname(addedToGroupContact.getLastname()).withMobilephone(addedToGroupContact.getMobilephone()).
                withWorkphone(addedToGroupContact.getWorkphone()).withHomephone(addedToGroupContact.getHomephone()).
                withAddress(addedToGroupContact.getAddress()).withEmail(addedToGroupContact.getEmail()).
                withEmail2(addedToGroupContact.getEmail2()).withEmail3(addedToGroupContact.getEmail3()).inGroup(addedGroup);
        Groups beforeGroups = contact.getGroups();
        app.goTo().homePage();
        app.contact().addContactToGroup(contact, addedGroupName);
        Groups afterGroups = contact.getGroups();
        assertThat(afterGroups, equalTo(beforeGroups.withAdded(addedGroup)));

    }
}