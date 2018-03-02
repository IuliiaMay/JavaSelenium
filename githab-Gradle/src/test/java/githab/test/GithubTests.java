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
        Github github = new RtGithub("e5baf6461639969b3865d0527d245b254c5870c7 ");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("IuliiaMay", "JavaSelenium")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
