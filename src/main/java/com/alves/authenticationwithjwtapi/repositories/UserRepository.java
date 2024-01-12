package com.alves.authenticationwithjwtapi.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.authenticationwithjwtapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public Optional<User> findByEmail(String email);
  public Optional<User> findByVerificationCode(UUID verificationCode);
  
}