package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {


    @Test
    public void testContactCreation() throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactCreationPage();
        ContactData contact = new ContactData().withFirstname("Maria").withLastname("Iakovenko").
                withMobilephone("+48666777545").withEmail("iakovenko.mariia3@gmail.com").withGroup("test1");
        app.contact().create((contact), true);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded
                (contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()))));


    }


}
