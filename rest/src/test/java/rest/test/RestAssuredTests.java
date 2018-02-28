package rest.test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

/**
 * Created by User on 27.02.2018.
 */
public class RestAssuredTests {

    @BeforeClass
    public void init() {

    RestAssured.authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");

}

    @Test
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(1);
        Set<Issues> oldIssues = getIssues();
        Issues createdIssue = new Issues().withSubject("Subject").withDescription("description");
        int issueId = createIssue(createdIssue);
        Set<Issues> newIssues = getIssues();
        oldIssues.add(createdIssue.withId(issueId));
        Assert.assertEquals(oldIssues, newIssues);

    }

    private int createIssue(Issues issue) throws IOException {
                String json = RestAssured.given().parameter("subject", issue.getSubject())
                .parameter("description", issue.getDescription())
                .post("http://demo.bugify.com/api/issues.json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        return  parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    private Set<Issues> getIssues() throws IOException {
        String json = RestAssured.get("http://demo.bugify.com/api/issues.json?page=1&limit=1000")
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issues>>(){}.getType());
    }


    public void skipIfNotFixed(int issueId) throws RemoteException,  MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws MalformedURLException,  RemoteException {
        String json = RestAssured.get("http://demo.bugify.com/api/issues/"+ issueId + ".json")
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issue = parsed.getAsJsonObject().get("issues")
                .getAsJsonArray().get(0);
       // JsonElement issue = issues.getAsJsonArray().get(0);
        String state = issue.getAsJsonObject().get("state_name").getAsString();
        if(state.equals("Resolved") || state.equals("Closed")){
            return false;
        }
        return true;
    }

}
