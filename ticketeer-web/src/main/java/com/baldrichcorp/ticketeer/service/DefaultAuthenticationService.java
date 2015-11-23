package com.baldrichcorp.ticketeer.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.SecureRandom;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baldrichcorp.ticketeer.model.UserPrincipal;
import com.baldrichcorp.ticketeer.repository.UserRepository;

@Service
public class DefaultAuthenticationService implements AuthenticationService {

  private static Logger logger = LoggerFactory.getLogger(DefaultAuthenticationService.class);
  private static final SecureRandom RANDOM;
  private static final int HASHING_ROUNDS = 10;

  static {
    try {
      RANDOM = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException(e);
    }
  }

  @Autowired
  private UserRepository userRepository;

  @Override
  public Principal authenticate(String handle, String password) {
    UserPrincipal principal = this.userRepository.findByHandle(handle);
    if (principal == null) {
      logger.warn("Authentication failed for non-existent user {}.", handle);
      return null;
    }

    if (!BCrypt.checkpw(password, new String(principal.getPassword(), StandardCharsets.UTF_8))) {
      logger.warn("Authentication failed for user {}.", handle);
      return null;
    }
    logger.debug("User {} successfully authenticated.", handle);
    return principal;
  }

  @Override
  @Transactional
  public void saveUser(UserPrincipal principal, String newPassword) {

    if (newPassword != null && newPassword.length() > 0) {
      String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
      principal.setPassword(BCrypt.hashpw(newPassword, salt).getBytes());
    }
    this.userRepository.save(principal);
  }

}
