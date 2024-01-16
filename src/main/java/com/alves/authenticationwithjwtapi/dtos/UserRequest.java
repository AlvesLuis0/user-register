package com.alves.authenticationwithjwtapi.dtos;

import com.alves.authenticationwithjwtapi.models.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(

  @NotNull(message = "Name cannot be null.")
  @NotBlank(message = "Name cannot be blank.")
  String name,

  @NotNull(message = "Email cannot be null.")
  @NotBlank(message = "Email cannot be blank.")
  @Email
  String email,

  @NotNull(message = "Password cannot be null.")
  @NotBlank(message = "Password cannot be blank.")
  @Size(min = 8, message = "The password must contain at least 8 characters")
  String password

) {

  public User toModel() {
    var user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    return user;
  }
  
}