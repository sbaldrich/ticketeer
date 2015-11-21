package com.baldrichcorp.ticketeer.repository;

import com.baldrichcorp.ticketeer.model.User;

public interface UserRepository extends GenericRepository<Long, User>{
  
  public String getPasswordForUser(String handle);
  
  public void addUser(String handle, String password);

}
