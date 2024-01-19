package com.alves.userregister.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailAlreadyBeingUsedException extends RuntimeException {

  public EmailAlreadyBeingUsedException(String email) {
    super(String.format("The email '%s' is already in use.", email));
  }
  
}