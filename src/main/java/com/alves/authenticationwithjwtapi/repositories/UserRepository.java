package com.alves.authenticationwithjwtapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alves.authenticationwithjwtapi.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public User findByEmail(String email);
  public User findByVerificationCode(String verificationCode);
  
}