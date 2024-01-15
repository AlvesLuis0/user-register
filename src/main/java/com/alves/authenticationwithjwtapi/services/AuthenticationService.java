package com.alves.authenticationwithjwtapi.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alves.authenticationwithjwtapi.dtos.AuthenticationRequest;
import com.alves.authenticationwithjwtapi.models.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  public String generateToken(AuthenticationRequest authenticationRequest) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(
      authenticationRequest.email(),
      authenticationRequest.password()
    );
    Authentication auth = authenticationManager.authenticate(usernamePassword);
    String token = tokenService.generateToken((User)auth.getPrincipal());
    return token;
  }
  
}