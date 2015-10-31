package com.baldrichcorp.ticketeer.repository;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserRepository implements UserRepository{
  
  private final HashMap<String, String> userDatabase = new HashMap<>();
  private Logger logger = LoggerFactory.getLogger(InMemoryUserRepository.class);
  
  public InMemoryUserRepository() {
    this.userDatabase.put("admin", "admin");
  }
  
  @Override
  public String getPasswordForUser(String handle) {
    return this.userDatabase.get("handle");
  }

  @Override
  public void addUser(String handle, String password) {
    logger.info("Adding user {} to repository", handle);
    this.userDatabase.put(handle, password);
  }
      
}
