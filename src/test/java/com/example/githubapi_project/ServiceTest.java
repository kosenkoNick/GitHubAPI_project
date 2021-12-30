package com.example.githubapi_project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.githubapi_project.service.impl.RepoServiceImpl;
import java.io.IOException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

  public GitHub gitHub;
  protected RepoServiceImpl repoService;

  @Autowired
  public ServiceTest(RepoServiceImpl repoService) throws IOException {
    this.gitHub = GitHub.connectAnonymously();
    this.repoService = repoService;
  }

  @BeforeEach
  @DisplayName("Check connection")
  public void checkConnection() {
    assertNotNull(this.gitHub, "Connection has been canceled");
  }

  @Test
  @DisplayName("Get all Repos for User")
  public void getUserReposTest() throws IOException {
    assertThrows(NullPointerException.class, () -> repoService.getUserRepos(null, "smth"));
    assertThrows(NullPointerException.class, () -> repoService.getUserRepos(this.gitHub, null));
    String expected = "[GitHubAPI_project, LR_1, Library, LibrarySpringCore_MVC, database_labs]";
    String actual = repoService.getUserRepos(this.gitHub, "kosenkoNick")
        .stream()
        .map(repo -> repo.getName())
        .collect(Collectors.toList())
        .toString();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("Get all Branches in Repo")
  public void getRepoBranchesTest() throws IOException {
    assertThrows(NullPointerException.class, () -> repoService.getRepoBranches(null));
    assertTrue(repoService.getUserRepos(this.gitHub, "kosenkoNick").stream()
        .filter(repo -> repo.getName().equals("LibrarySpringCore_MVC")).toList().get(0).getBranches().isEmpty());


  }


}