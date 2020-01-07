package ru.stqa.pft.mantis.tests;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }
@Test
public void testResetPassword() throws IOException, javax.mail.MessagingException, MessagingException {
   HashSet< UserData> allUsers = app.db().users();
  UserData user = new UserData().withId(allUsers.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()).
          withEmail(allUsers.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getEmail()).
         withPassword(allUsers.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getPassword());
    String email = user.getEmail();
   String username= user.getUsername();
   // String username= "user88";
   // String email ="user88@localhost.localdomain";
   int userId = user.getId();
    String adminLogin ="administrator";
    String adminPassword = "root";
    String userPassword = "password";
    String userRealname = "realname";
    //app.james().createUser(user,password);
    app.resetPassword().start(adminLogin,adminPassword,userId);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 40000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
   app.resetPassword().finish(confirmationLink,userRealname,userPassword);
    assertTrue(app.newSession().login(username,userPassword));
}
    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
}
