package com.alves.authenticationwithjwtapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontController {

  @GetMapping("/login")
  public String loginPage() {
    return "/login.html";
  }

  @GetMapping("/register")
  public String registerPage() {
    return "/register.html";
  }

  @GetMapping("/home")
  public String homePage() {
    return "/home.html";
  }
  
}