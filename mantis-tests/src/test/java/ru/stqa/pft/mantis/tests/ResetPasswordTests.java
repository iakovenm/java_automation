package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
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
    UserData user = new UserData();
        for  ( UserData u:allUsers) {
        if (u.getId()!=1){
            user=u;
            break;
        }
    }
    String email = user.getEmail();
   String username= user.getUsername();
   int userId = user.getId();
    String adminLogin ="administrator";
    String adminPassword = "root";
    String userPassword = "password";
    String userRealname = "realname";
    app.resetPassword().start(adminLogin,adminPassword,userId);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 40000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
   app.resetPassword().finish(confirmationLink,userRealname,userPassword);
    HashSet< UserData> allUsersAfter = app.db().users();
    for (UserData u: allUsersAfter) {
        if (u.getId()==userId){
            username =u.getUsername();
        }
    }
    assertTrue(app.newSession().login(username,userPassword));
}
    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
}}
