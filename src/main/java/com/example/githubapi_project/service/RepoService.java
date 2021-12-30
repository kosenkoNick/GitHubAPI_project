package com.example.githubapi_project.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

public interface RepoService {

  List<GHRepository> getUserRepos(GitHub github, String username) throws IOException;

  Set<String> getRepoBranches(GHRepository repo) throws IOException;

}
