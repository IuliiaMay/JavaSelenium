package rest.test;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;

/**
 * Created by User on 27.02.2018.
 */
public class RestTests {
    @Test
    public void testCreateIssue() throws IOException {
        Set<Issues> oldIssues = getIssues();
        Issues createdIssue = new Issues().withSubject("Subject").withDescription("description");
        int issueId = createIssue(createdIssue);
        Set<Issues> newIssues = getIssues();
        oldIssues.add(createdIssue.withId(issueId));
        Assert.assertEquals(oldIssues, newIssues);

    }

    private int createIssue(Issues issue) throws IOException {
        String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", issue.getSubject()),
                          new BasicNameValuePair("description", issue.getDescription())))
                .returnContent()
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
        return  parsed.getAsJsonObject().get("issue_id").getAsInt();

    }

    private Set<Issues> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json?page=1&limit=1000"))
                .returnContent()
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issues>>(){}.getType());

    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
    }
}
