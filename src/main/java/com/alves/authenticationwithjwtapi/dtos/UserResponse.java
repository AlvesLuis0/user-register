package com.alves.authenticationwithjwtapi.dtos;

import com.alves.authenticationwithjwtapi.models.User;

public record UserResponse(Long id, String name, String email) {

  public static UserResponse create(User user) {
    return new UserResponse(
      user.getId(),
      user.getName(),
      user.getEmail()
    );
  } 

}