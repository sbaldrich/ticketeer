package com.baldrichcorp.ticketeer.service;

import com.baldrichcorp.ticketeer.model.UserPrincipal;

public interface UserService {
  UserPrincipal getByHandle(String handle);
}
