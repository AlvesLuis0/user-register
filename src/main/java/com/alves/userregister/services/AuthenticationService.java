package com.alves.userregister.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alves.userregister.dtos.LoginRequest;
import com.alves.userregister.models.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  public String generateToken(LoginRequest login) {
    var userPass = new UsernamePasswordAuthenticationToken(
      login.email(),
      login.password()
    );
    Authentication auth = authenticationManager.authenticate(userPass);
    String token = tokenService.generateToken((User)auth.getPrincipal());
    return token;
  }
  
}