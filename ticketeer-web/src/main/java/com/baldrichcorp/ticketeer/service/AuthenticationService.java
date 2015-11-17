package com.baldrichcorp.ticketeer.service;

import java.security.Principal;

public interface AuthenticationService {
  
  void register(String handle, String password);
  
  Principal authenticate(String username, String password);
}
