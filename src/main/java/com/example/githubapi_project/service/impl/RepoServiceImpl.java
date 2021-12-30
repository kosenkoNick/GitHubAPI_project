package com.example.githubapi_project.service.impl;

import com.example.githubapi_project.service.RepoService;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Service;

@Service
public class RepoServiceImpl implements RepoService {

  @Override
  public Collection<GHRepository> getUserRepos(GitHub github, String username) throws IOException {
    if (github == null || username == null) {
      throw new NullPointerException("Invalid parameters: connection or username");
    }
    GHUser user = github.getUser(username);
    Map<String, GHRepository> repositoryMap = user.getRepositories();

    return repositoryMap.values();

  }

  @Override
  public Set<String> getRepoBranches(GHRepository repo) throws IOException {
    if (repo == null) {
      throw new NullPointerException("Invalid parameters: connection or username");
    }
    Map<String, GHBranch> branchesMap = repo.getBranches();
    return branchesMap.keySet();
  }
}
