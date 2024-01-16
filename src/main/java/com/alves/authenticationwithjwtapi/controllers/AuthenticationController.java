package com.alves.authenticationwithjwtapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.authenticationwithjwtapi.dtos.LoginRequest;
import com.alves.authenticationwithjwtapi.dtos.LoginResponse;
import com.alves.authenticationwithjwtapi.services.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest auth) {
    String token = authenticationService.generateToken(auth);
    return ResponseEntity.ok().body(new LoginResponse(token));
  }
  
}