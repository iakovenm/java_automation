package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;


public class TestBase {


    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

   /* boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        IssueData issue = app.soap().getIssue(issueId);
        String status = issue.getStatus().getName();
        if (status!="closed"){
        return true;

        } return false;
    }*/

    boolean isIssueOpen(int issueId) throws IOException, ServiceException {
        Issue issue = app.rest().getIssue(issueId);
        JsonElement parsed = new JsonParser().parse(String.valueOf(issue));
        //return parsed.getAsJsonObject().get("issue_id").getAsInt();
        String status = parsed.getAsJsonObject().get("status").getAsString();
        if (status!="closed"){
            return true;

        } return false;
    }



    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak","config_inc.php");
        app.stop();
    }
    
    public void skipIfNotFixed(int issueId) throws IOException, ServiceException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
