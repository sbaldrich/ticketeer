package com.baldrichcorp.ticketeer.repository;

public interface UserRepository {
  
  public String getPasswordForUser(String handle);
  
  public void addUser(String handle, String password);

}
