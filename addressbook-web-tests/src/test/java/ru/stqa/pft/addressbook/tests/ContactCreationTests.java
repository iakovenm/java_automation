package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try( BufferedReader reader =new BufferedReader( new FileReader(new File("src/test/resources/contacts.json")))
        ) {
        String json = "";
        String line = reader.readLine();
        while (line !=null){
            json +=line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g)-> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        app.goTo().contactCreationPage();
        /*File photo = new File("src/test/resources/pastedImage.png");
        ContactData contact = new ContactData().withFirstname("Maria").withLastname("Iakovenko").
                withMobilephone("+48666777545").
                withEmail("iakovenko.mariia3@gmail.com").withGroup("test1").withPhoto(photo);*/
        app.contact().create((contact), true);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        assertThat(after, equalTo(before.withAdded
                (contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()))));


    }


}


