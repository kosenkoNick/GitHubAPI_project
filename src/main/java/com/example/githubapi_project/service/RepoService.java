package com.example.githubapi_project.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public interface RepoService {

  public Collection<GHRepository> getUserRepos(GitHub github, String username) throws IOException;

  public Set<String> getRepoBranches(GHRepository repo) throws IOException;

}
