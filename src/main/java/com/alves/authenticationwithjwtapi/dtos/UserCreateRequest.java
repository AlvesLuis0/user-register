package com.alves.authenticationwithjwtapi.dtos;

import com.alves.authenticationwithjwtapi.models.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(

  @NotBlank(message = "Name is mandatory.")
  String name,

  @NotBlank(message = "Email is mandatory.")
  @Email
  String email,

  @NotBlank(message = "Password is mandatory.")
  @Size(min = 8, message = "The password must contain at least 8 characters")
  String password

) {

  public User toModel() {
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    return user;
  }
  
}