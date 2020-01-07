package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
        }
       int count=0;
        for (ContactData c: contacts) {
        if (c.getGroups().size()<groups.size()){
            count++;
            return;}
        }

        if (count==0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("header").withFooter("footer"));
            return;
        }


         if (contacts.iterator().next().getGroups().contains(groups.iterator().next())) {
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
       ContactData addedToGroupContact = before.stream().min((o1, o2) -> Integer.compare(o1.getGroups().size(), o2.getGroups().size())).get();
         //       withId(g.getId()).withFirstname(g.getFirstname()).withLastname(g.getLastname()).inGroup(g.getGroups().removeIf()).collect(Collectors.toSet())));
       //ContactData addedToGroupContact = before.iterator().next();

       // ContactData contact = new ContactData().withId(addedToGroupContact.getId()).withFirstname(addedToGroupContact.getFirstname()).
               // withLastname(addedToGroupContact.getLastname()).withMobilephone(addedToGroupContact.getMobilephone()).
               // withWorkphone(addedToGroupContact.getWorkphone()).withHomephone(addedToGroupContact.getHomephone()).
               // withAddress(addedToGroupContact.getAddress()).withEmail(addedToGroupContact.getEmail()).
              //  withEmail2(addedToGroupContact.getEmail2()).withEmail3(addedToGroupContact.getEmail3()).inGroup(addedGroup);
        for (ContactData c: before) {
            if (c.getGroups().size()<groups.size()){
                addedToGroupContact = c;

            }
        }

            System.out.println(addedToGroupContact);
            Groups beforeGroups = addedToGroupContact.getGroups();
            for (ContactData d: before) {
               if (d.getId()==addedToGroupContact.getId()){
                   beforeGroups = d.getGroups();
                }
                }

            System.out.println(beforeGroups);
        app.goTo().homePage();
        app.contact().addContactToGroup(addedToGroupContact, addedGroupName);
        Groups afterGroups = addedToGroupContact.getGroups();
            for (ContactData d: before) {
                if(d.getId()==addedToGroupContact.getId()){
                    afterGroups = d.getGroups();
                }
            }
        System.out.println(afterGroups);
            System.out.println(beforeGroups.withAdded(addedGroup));
           assertThat(afterGroups, equalTo(beforeGroups.withAdded(addedGroup)));}}



