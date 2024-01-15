package com.alves.authenticationwithjwtapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alves.authenticationwithjwtapi.dtos.AuthenticationRequest;
import com.alves.authenticationwithjwtapi.dtos.AuthenticationResponse;
import com.alves.authenticationwithjwtapi.services.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
    String token = authenticationService.generateToken(authenticationRequest);
    return ResponseEntity.ok().body(new AuthenticationResponse(token));
  }
  
}