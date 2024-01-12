package com.alves.authenticationwithjwtapi.dtos;

import com.alves.authenticationwithjwtapi.models.User;

public record UserCreateRequest(String name, String email, String password) {

  public User toModel() {
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    return user;
  }
  
}