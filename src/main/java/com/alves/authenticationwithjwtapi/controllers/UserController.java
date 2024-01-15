package com.alves.authenticationwithjwtapi.controllers;

import java.util.UUID;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.authenticationwithjwtapi.dtos.UserCreateRequest;
import com.alves.authenticationwithjwtapi.dtos.UserResponse;
import com.alves.authenticationwithjwtapi.models.User;
import com.alves.authenticationwithjwtapi.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserResponse> registerUser(@RequestBody UserCreateRequest userCreateRequest) {
    User user = userCreateRequest.toModel();
    User userRegistered = userService.registerUser(user);
    return ResponseEntity.ok().body(UserResponse.create(userRegistered));
  }

  @GetMapping("/verify")
  public ResponseEntity<Object> verifyUser(@Param("code") UUID code) {
    userService.verifyUser(code);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/ping")
  public ResponseEntity<Object> ping() {
    return ResponseEntity.ok().build();
  }
  
}