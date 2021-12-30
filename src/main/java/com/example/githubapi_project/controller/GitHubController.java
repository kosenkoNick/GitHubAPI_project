package com.example.githubapi_project.controller;

import com.example.githubapi_project.service.RepoService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitHubController {

  protected RepoService repoService;

  public GitHubController(RepoService repoService) {
    this.repoService = repoService;
  }

/*  @GetMapping("/github/{username}")
  @ResponseBody
  protected String getUserData(@PathVariable("username") String username)
      throws IOException {
    GitHub github = GitHub.connectAnonymously();
    Map<String, Set<String>> resultMap = new HashMap<>();
    Collection<GHRepository> repositoryList = repoService.getUserRepos(github, username);
    repositoryList.forEach(
        repo -> {
          try {
            resultMap.put(repo.getName(), repoService.getRepoBranches(repo));
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
    );
    System.out.println(resultMap);
    return resultMap.toString();
  }*/

  @GetMapping("/github/{username}")
  @ResponseBody
  protected void getUserData(@PathVariable("username") String username,
                               HttpServletResponse response)
      throws IOException {
    GitHub github = GitHub.connectAnonymously();
    Map<String, Set<String>> resultMap = new HashMap<>();
    List<GHRepository> repositoryList = repoService.getUserRepos(github, username);
    StringBuilder builder = new StringBuilder();
    repositoryList.forEach(
        repo -> {
          try {
            builder
                .append("\nRepository:\t")
                .append(repo.getName())
                .append("\n")
                .append("Branches:\t")
                .append(repoService.getRepoBranches(repo))
                .append("\n");
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
    );
    response.addHeader("content-type", "text/plain; charset=utf-8");
    response.setStatus(200);

    PrintWriter out = response.getWriter();
    out.println(builder.toString());
  }
}
