package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.*;

public class ResetPasswordHelper extends HelperBase {

    public ResetPasswordHelper(ApplicationManager app){
        super(app);
    }

    public void start(String username, String password, int userId) {
        wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
        type(name("username"),username);
        click(By.cssSelector("input[type='submit']"));
        type(name("password"), password);
        click(By.cssSelector("input[type='submit']"));
        click(By.cssSelector("a[href = '/mantisbt-2.23.0/manage_overview_page.php']"));
        click(By.cssSelector("a[href = '/mantisbt-2.23.0/manage_user_page.php']"));
        click(By.cssSelector(String.format("a[href = 'manage_user_edit_page.php?user_id=%s'", userId)));
        click(By.cssSelector("input[value='Reset Password']"));
        //type(name("email"), email);
       // click(cssSelector("input[value='Signup']"));
    }

    public void finish(String confirmationLink, String realname, String password){
            //, String password) {
        wd.get(confirmationLink);
        type(name("realname"),realname);
        type(name("password"),password);
        type(name("password_confirm"),password);
       click(cssSelector("button[type='submit']"));
    }
}
