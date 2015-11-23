package com.baldrichcorp.ticketeer.service;

import java.security.Principal;

import com.baldrichcorp.ticketeer.model.UserPrincipal;



public interface AuthenticationService {
  
  void saveUser(UserPrincipal principal, String newPassword);
  
  Principal authenticate(String username, String password);
}
