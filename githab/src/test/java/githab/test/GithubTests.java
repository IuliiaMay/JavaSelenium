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
        Github github = new RtGithub("07aa937f1b5027422926758363ccb2d1e7f2e7b7 ");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("IuliiaMay", "JavaSelenium")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
