package com.baldrichcorp.ticketeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baldrichcorp.ticketeer.model.UserPrincipal;
import com.baldrichcorp.ticketeer.repository.UserRepository;

@Service
public class DefaultUserService implements UserService{

  @Autowired
  private UserRepository userRepository;
  
  @Override
  public UserPrincipal getByHandle(String handle) {
    return userRepository.findByHandle(handle);
  }

}
