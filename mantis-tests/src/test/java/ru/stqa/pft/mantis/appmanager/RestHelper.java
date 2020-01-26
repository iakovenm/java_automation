package ru.stqa.pft.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Request;
import ru.stqa.pft.mantis.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {
    private ApplicationManager app;
    public RestHelper (ApplicationManager app){
        this.app = app;
    }

    public void init(){
        RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
}


    private Set<Issue> getIssues() throws IOException {
        // String json= getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues.json")).returnContent().asString();
        String json=RestAssured.get(" http://bugify.stqa.ru/api/issues/.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues,  new TypeToken<Set<Issue>>(){}.getType());
    }

   /* public Issue getIssue(int issueId) throws IOException {
      Set <Issue> issues = getIssues();
      Issue issue = issues.iterator().next();
      for (Issue i: issues) {
          if (i.getId()==issueId){
              issue =i;
          }
        }
        return issue;
    }*/

}
