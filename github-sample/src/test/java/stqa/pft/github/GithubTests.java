package stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("5fb7f48b6b445e96080c903b1c2d52b200e2263c");
        RepoCommits commits = github.repos()
                .get(new Coordinates.Simple("iakovenm", "java_automation")).commits();
        for (RepoCommit commit:commits.iterate(new ImmutableMap.Builder<String,String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
