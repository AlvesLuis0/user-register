package com.alves.authenticationwithjwtapi.config.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alves.authenticationwithjwtapi.exceptions.UserNotFoundException;
import com.alves.authenticationwithjwtapi.repositories.UserRepository;
import com.alves.authenticationwithjwtapi.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = recoverToken(request);
    boolean hasToken = token != null;
    if(hasToken) {
      String subject = tokenService.validateToken(token);
      UserDetails user = userRepository.findByEmail(subject)
        .orElseThrow(() -> new UserNotFoundException("email", subject));
      var authentication = new UsernamePasswordAuthenticationToken(
        user, null,
        user.getAuthorities()
      );
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");
    boolean hasAuthHeader = authHeader != null;
    if(hasAuthHeader) {
      return authHeader.replace("Bearer ", "");
    }
    return null;
  }
  
}