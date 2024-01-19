package com.alves.userregister.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String field, Object value) {
    super(String.format("User with %s '%s' not found.", field, value));
  }
  
}