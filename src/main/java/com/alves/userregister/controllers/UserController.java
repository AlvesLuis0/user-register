package com.alves.userregister.controllers;

import java.util.UUID;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.userregister.dtos.UserRequest;
import com.alves.userregister.dtos.UserResponse;
import com.alves.userregister.models.User;
import com.alves.userregister.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRequest userRequest) {
    User user = userRequest.toModel();
    User userRegistered = userService.registerUser(user);
    return ResponseEntity.ok(UserResponse.create(userRegistered));
  }

  @GetMapping("/verify")
  public ResponseEntity<String> verifyUser(@Param("code") UUID code) {
    userService.verifyUser(code);
    return ResponseEntity.ok(
      "User verified successfully! You can log now <a href=\"/login\">here</a>"
    );
  }

  @GetMapping("/ping")
  public ResponseEntity<Object> ping() {
    return ResponseEntity.ok("User logged!");
  }
  
}