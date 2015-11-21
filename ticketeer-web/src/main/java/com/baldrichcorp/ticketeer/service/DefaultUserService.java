package com.baldrichcorp.ticketeer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baldrichcorp.ticketeer.model.User;
import com.baldrichcorp.ticketeer.repository.UserRepository;

@Service
public class DefaultUserService implements UserService{

  @Autowired
  private UserRepository userRepository;
  
  @Override
  public User getByHandle(String handle) {
    return userRepository.getAll().stream().filter(user -> user.getHandle().equals(handle)).findFirst().get();
  }

}
