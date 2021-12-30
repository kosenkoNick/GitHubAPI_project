package com.example.githubapi_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping({"/", "/home"})
  protected String hello(){
    return "Hello everyone!";
  }
}
