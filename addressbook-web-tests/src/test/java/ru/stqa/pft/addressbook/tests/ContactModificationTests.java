package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {
   @BeforeMethod
    public void ensurePreconditions (){
        if (app.db().contacts().size()==0){
        app.goTo().homePage();
        app.contact().create(new ContactData().withFirstname("Olga").withLastname("sharko").
                withMobilephone("+48666777545").withEmail("iakovenko.mariia3@gmail.com").
                withAddress("podlanska").withEmail2("email2").withEmail3("email3").
                withHomephone("homephone").withWorkphone("workphone"), true);
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Maria").withLastname("Iakovenko").
                withMobilephone("+48666777545").withEmail("iakovenko.mariia3@gmail.com").
                withAddress("podlanska").withEmail2("email2").withEmail3("email3").
                withHomephone("homephone").withWorkphone("workphone");
        app.goTo().homePage();
        app.contact().modify(contact);
        Contacts after =app.db().contacts();
        assertThat(app.contact().count(),equalTo( before.size()));
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }

    }

