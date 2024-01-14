package com.alves.authenticationwithjwtapi.services;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alves.authenticationwithjwtapi.dtos.UserResponse;
import com.alves.authenticationwithjwtapi.exceptions.EmailAlreadyBeingUsedException;
import com.alves.authenticationwithjwtapi.exceptions.UserNotFoundException;
import com.alves.authenticationwithjwtapi.models.User;
import com.alves.authenticationwithjwtapi.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserResponse registerUser(User user) {
    userRepository.findByEmail(user.getEmail())
      .ifPresent(s -> { throw new EmailAlreadyBeingUsedException(user.getEmail()); });
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEnabled(false);
    user.setVerificationCode(UUID.randomUUID());
    // TODO: send email
    User savedUser = userRepository.save(user);
    return new UserResponse(
      savedUser.getId(),
      savedUser.getName(),
      savedUser.getEmail()
    );
  }

  public void verifyUser(UUID verificationCode) {
    User user = userRepository.findByVerificationCode(verificationCode)
      .orElseThrow(() -> new UserNotFoundException("verification code", verificationCode));
    user.setVerificationCode(null);
    user.setEnabled(true);
    userRepository.save(user);
  }

  // load by email*
  @Override
  public UserDetails loadUserByUsername(String email) {
    return userRepository.findByEmail(email)
      .orElseThrow(() -> new UserNotFoundException("email", email));
  }
  
}