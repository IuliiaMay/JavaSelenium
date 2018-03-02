package githab.test;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;
import java.io.IOException;

/**
 * Created by User on 27.02.2018.
 */
public class GithubTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("d3dc82120b2666236177976838d637e9e461f1cf");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("IuliiaMay", "JavaSelenium")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
