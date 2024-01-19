package com.alves.userregister.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequest(

  @NotNull(message = "Email cannot be null.")
  @Email
  String email,

  @NotNull(message = "Password cannot be null.")
  @NotBlank(message = "Password cannot be blank.")
  @Size(min = 8, message = "The password must contain at least 8 characters")
  String password

) {

  // 
  
}