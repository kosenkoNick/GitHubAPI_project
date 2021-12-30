package com.example.githubapi_project.controller;

import java.io.IOException;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GitHubController {
  @GetMapping("/github/{username}")
  protected String getUserData(@PathVariable("username") String username) throws IOException {
    GitHub github = GitHub.connectAnonymously();
    GHUser user = github.getUser(username);
    StringBuilder str = new StringBuilder();
    user.getRepositories()
        .entrySet()
        .forEach(entry -> {
          try {
            str
                .append(entry.getKey())
                .append(":\n")
                .append(entry.getValue().getBranches().keySet())
                .append("\n\n");
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
    System.out.println(str.toString());
    return str.toString();
  }
}
