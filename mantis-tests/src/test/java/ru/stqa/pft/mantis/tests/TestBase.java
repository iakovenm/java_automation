package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.*;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
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

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().
                getMantisConnectPort(new URL(app.getProperty("soap.url")));
    }

 /*boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        IssueData issue = mc.mc_issue_get("administrator", "root",BigInteger.valueOf(issueId));
        String status = issue.getStatus().getName();
        if (status.equals("closed")){
        return false;

        } return true;
    }*/
    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");}
    boolean isIssueOpen(int issueId) throws IOException, ServiceException {
        String json= getExecutor().execute(Request.Get(String.format("http://bugify.stqa.ru/api/issues/%s.json",issueId))).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement parsedFirstelement= parsed.getAsJsonObject().get("issues");
        String state=parsedFirstelement.getAsJsonArray().get(Integer.parseInt("state_name")).getAsString();
        if (state.equals("closed")){
            return false;
        } return true;
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
