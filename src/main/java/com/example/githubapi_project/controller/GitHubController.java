package com.example.githubapi_project.controller;

import com.example.githubapi_project.service.RepoService;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitHubController {

  protected RepoService repoService;

  public GitHubController(RepoService repoService) {
    this.repoService = repoService;
  }

  @GetMapping("/github/{username}")
  protected Map<String, Set<String>> getUserData(@PathVariable("username") String username)
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
    return resultMap;

  }
}
