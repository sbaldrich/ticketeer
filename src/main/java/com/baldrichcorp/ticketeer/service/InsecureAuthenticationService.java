package com.baldrichcorp.ticketeer.service;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baldrichcorp.ticketeer.model.UserPrincipal;
import com.baldrichcorp.ticketeer.repository.UserRepository;

@Service
public class InsecureAuthenticationService implements AuthenticationService {

  private static Logger logger = LoggerFactory.getLogger(InsecureAuthenticationService.class);
  
  @Autowired
  private UserRepository userRepository;

  @Override
  public void register(String handle, String password) {
    userRepository.addUser(handle, password);
  }

  @Override
  public Principal authenticate(String username, String password) {
    String currentPassword = this.userRepository.getPasswordForUser(username);
  
    if (currentPassword == null) {
      logger.warn("Authentication failed for non-existent user {}.", username);
      return null;
    }

    if (!currentPassword.equals(password)) {
      logger.warn("Authentication failed for user {}.", username);
      return null;
    }

    logger.debug("User {} successfully authenticated.", username);

    return new UserPrincipal(username);
  }

}
