package com.alves.userregister.services;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alves.userregister.exceptions.EmailAlreadyBeingUsedException;
import com.alves.userregister.exceptions.UserNotFoundException;
import com.alves.userregister.models.User;
import com.alves.userregister.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final MailService mailService;
  private final PasswordEncoder passwordEncoder;

  public User registerUser(User user) {
    // validating
    userRepository.findByEmail(user.getEmail())
      .ifPresent(s -> { throw new EmailAlreadyBeingUsedException(user.getEmail()); });
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setEnabled(false);
    user.setVerificationCode(UUID.randomUUID());
    // email
    mailService.sendEmail(
      user.getEmail(),
      "User Verification",
      String.format(
        "Click the link http://localhost:8080/user/verify?code=%s to verify your account!",
        user.getVerificationCode()
      )
    );
    // saving user
    return userRepository.save(user);
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