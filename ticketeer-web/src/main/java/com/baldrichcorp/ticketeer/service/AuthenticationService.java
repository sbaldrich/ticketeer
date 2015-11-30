package com.baldrichcorp.ticketeer.service;

import java.security.Principal;

import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.baldrichcorp.ticketeer.model.UserPrincipal;

@Validated
public interface AuthenticationService {
  
  void saveUser(UserPrincipal principal, String newPassword);
  
  Principal authenticate(String username, @Size(min = 1, message = "ñañaña") String password);
}
