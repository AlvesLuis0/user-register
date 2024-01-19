package com.alves.userregister.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alves.userregister.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService {

  private final String ISSUER = "user-register";
  private final Algorithm ALGORITHM;

  public TokenService(@Value("jwt.secret") String SECRET) {
    this.ALGORITHM = Algorithm.HMAC256(SECRET);
  }

  public String generateToken(User user) {
    try {
      return JWT.create()
        .withIssuer(ISSUER)
        .withSubject(user.getEmail())
        .withExpiresAt(getExpirationDate())
        .sign(ALGORITHM);
    }
    catch(JWTCreationException exception) {
      throw new RuntimeException("Error while creating token.", exception);
    }
  }

  public String validateToken(String token) {
    try {
      return JWT.require(ALGORITHM)
        .withIssuer(ISSUER)
        .build()
        .verify(token)
        .getSubject();
    }
    catch(JWTVerificationException exception) {
      throw new RuntimeException("Invalid token.");
    }
  }

  private Instant getExpirationDate() {
    return LocalDateTime.now()
      .plusMinutes(5)
      .toInstant(ZoneOffset.of("-03:00"));
  }
  
}